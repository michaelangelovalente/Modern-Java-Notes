public class BoxDemo {

    public static <U> void addBox(U u,
            java.util.List<Box<U>> boxes) {
        Box<U> box = new Box<>();
        box.setT(u);
        boxes.add(box);

    }

    public static <U> void outputBoxes(java.util.List<Box<U>> boxes) {
        int counter = 0;
        for (Box<U> box : boxes) {
            U boxC = box.getT();
            System.out.println(String.format("Box # %d contains [%s]", counter++, boxC.toString()));
        }
    }

    public static void main(String[] args) {
        java.util.ArrayList<Box<Integer>> listOfIntBoxes = new java.util.ArrayList<>();

        BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntBoxes);
        BoxDemo.addBox(Integer.valueOf(20), listOfIntBoxes);
        BoxDemo.addBox(Integer.valueOf(30), listOfIntBoxes);
        BoxDemo.outputBoxes(listOfIntBoxes);
    }
}
