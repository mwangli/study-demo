package tree;

/**
 * @author mwangli
 * @date 2022/2/28 13:51
 */
public class TreeDemo {

    public static void main(String[] args) {
        TreeNode sj = new TreeNode("1", "sj");
        TreeNode wy = new TreeNode("2", "wy");
        sj.setLeft(wy);
        TreeNode lj = new TreeNode("3", "lj");
        sj.setRight(lj);
        TreeNode lc = new TreeNode("4", "lc");
        lj.setRight(lc);
        TreeNode gs = new TreeNode("5", "gs");
        lj.setLeft(gs);
//        // 1-2-3-5-4
//        sj.preList();
//        // 2-1-5-3-4
//        sj.midList();
//        // 2-5-4-3-1
//        sj.sufList();
//        System.out.println(sj.preSearch("gs"));
//        System.out.println(sj.midSearch("gs"));
//        System.out.println(sj.sufSearch("gs1"));
        sj.del("1");
        sj.preList();
    }

    static class TreeNode {
        private String id;
        private String name;
        private TreeNode left;
        private TreeNode right;

        private void preList() {
            System.out.println(this);
            TreeNode left = this.getLeft();
            if (left != null) left.preList();
            TreeNode right = this.getRight();
            if (right != null) right.preList();
        }

        private void del(String id) {
            if (this.getLeft() != null && this.getLeft().getId().equals(id)) {
                this.left = null;
                return;
            }
            if (this.getRight() != null && this.getRight().getId().equals(id)) {
                this.right = null;
                return;
            }
            if (this.getLeft() != null) {
                this.getLeft().del(id);
            }
            if (this.getRight() != null) {
                this.getRight().del(id);
            }
        }

        private TreeNode preSearch(String name) {
            if (name.equals(this.getName())) return this;
            TreeNode res = null;
            TreeNode left = this.getLeft();
            if (left != null) res = left.preSearch(name);
            if (res != null) return res;
            TreeNode right = this.getRight();
            if (right != null) res = right.preSearch(name);
            return res;
        }

        private TreeNode midSearch(String name) {
            TreeNode res = null;
            TreeNode left = this.getLeft();
            if (left != null) res = left.midSearch(name);
            if (res != null) return res;
            if (name.equals(this.getName())) return this;
            TreeNode right = this.getRight();
            if (right != null) res = right.midSearch(name);
            return res;
        }

        private TreeNode sufSearch(String name) {
            TreeNode res = null;
            TreeNode left = this.getLeft();
            if (left != null) res = left.sufSearch(name);
            if (res != null) return res;
            TreeNode right = this.getRight();
            if (right != null) res = right.sufSearch(name);
            if (res != null) return res;
            if (name.equals(this.getName())) return this;
            return null;
        }

        private void midList() {
            TreeNode left = this.getLeft();
            if (left != null) left.midList();
            System.out.println(this);
            TreeNode right = this.getRight();
            if (right != null) right.midList();
        }

        private void sufList() {
            TreeNode left = this.getLeft();
            if (left != null) left.sufList();
            TreeNode right = this.getRight();
            if (right != null) right.sufList();
            System.out.println(this);
        }

        public TreeNode(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
