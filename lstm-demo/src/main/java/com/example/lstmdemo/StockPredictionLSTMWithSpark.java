import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.LSTM;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.spark.api.TrainingMaster;
import org.deeplearning4j.spark.impl.paramavg.ParameterAveragingTrainingMaster;
import org.deeplearning4j.spark.parameterserver.training.SharedTrainingMaster;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.ArrayList;
import java.util.List;

public class StockPredictionLSTMWithSpark {

    public static void main(String[] args) {

        SparkSession sparkSession = SparkSession.builder()
                .appName("StockPredictionLSTMWithSpark")
                .master("local[*]")  // 使用本地Spark模式，你可以更改为集群地址
                .getOrCreate();

        JavaSparkContext sc = new JavaSparkContext(sparkSession.sparkContext());

        // 参数配置
        int lstmLayerSize = 50;
        int batchSizePerWorker = 32;
        int numEpochs = 50;
        int numFeatures = 1;
        int numOutputs = 1;

        List<Double> stockPrices = generateStockPrices(1000);
        List<DataSet> dataList = generateData(stockPrices, 20);

        JavaRDD<DataSet> rdd = sc.parallelize(dataList);

        TrainingMaster trainingMaster = new ParameterAveragingTrainingMaster.Builder(batchSizePerWorker)
                .workerPrefetchNumBatches(2)  // 启用预取
                .build();

        MultiLayerNetwork model = new NeuralNetConfiguration.Builder()
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(Updater.RMSPROP)
                .weightInit(WeightInit.XAVIER)
                .list()
                .layer(0, new LSTM.Builder()
                        .nIn(numFeatures)
                        .nOut(lstmLayerSize)
                        .activation("tanh")
                        .build())
                .layer(1, new DenseLayer.Builder()
                        .nIn(lstmLayerSize)
                        .nOut(numOutputs)
                        .activation("identity")
                        .build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                        .activation("identity")
                        .nIn(numOutputs)
                        .nOut(numOutputs)
                        .build())
                .pretrain(false).backprop(true).build();

        MultiLayerNetwork trainedModel = new SparkDl4jMultiLayer(sc, model, trainingMaster)
                .fit(rdd);

        List<Double> testData = stockPrices.subList(stockPrices.size() - 20, stockPrices.size());
        INDArray input = Nd4j.create(testData).reshape(1, numFeatures);
        INDArray output = trainedModel.output(input);
        System.out.println("预测的下一个股票价格: " + output.getDouble(0));

        sc.close();
        sparkSession.close();
    }

    private static List<Double> generateStockPrices(int numDataPoints) {
        List<Double> prices = new ArrayList<>();
        double price = 100.0;
        for (int i = 0; i < numDataPoints; i++) {
            price += Math.random() * 10 - 5;
            prices.add(price);
        }
        return prices;
    }

    private static List<DataSet> generateData(List<Double> prices, int sequenceLength) {
        List<DataSet> dataList = new ArrayList<>();
        for (int i = 0; i < prices.size() - sequenceLength; i++) {
            INDArray input = Nd4j.create(sequenceLength, 1);
            INDArray label = Nd4j.create(sequenceLength, 1);
            for (int j = 0; j < sequenceLength; j++) {
                input.putScalar(new int[]{j, 0}, prices.get(i + j));
                label.putScalar(new int[]{j, 0}, prices.get(i + j + 1));
            }
            dataList.add(new DataSet(input, label));
        }
        return dataList;
    }
}
