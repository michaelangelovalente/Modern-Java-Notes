public class CreatingYourOwnCollector {

    public static void main(String[] args) {
        // #### Four basic components of a collector
        // - First component (SUPPLIER): container in which the elemetns of the stream
        // will be collected. e.g ArrayList Hashset ecc..
        // the creation of these can be modeled with an instance of Supplier

        // - Second component (ACCUMULATOR): is an Operation that models the adding of a
        // single element from the stream to the container.
        // This component is modeled by a BiConsumer (accumulator) that takes 2
        // arguments: the container, the element of the stream that should be added to
        // the partially filled container

        // On parallelization:
        // The way this works in the Collector API is that a stream is divided in
        // substreams
        // and each will will be collected in its own instance of the container.

        // The final stage when the substreams are processed you have several containers
        // with the processed elements.
        //

        // Third Component (Combiner) - Modelled by Binary Operator, used to merge the
        // containers together. It take
        // It takes two partially fillede contianers and returns one.
        // The BinaryOperator is modeled by the BiConsumer

        // The fourth component (Finisher) -

        // #### Collecting primitive types in a Collection
        // Example specialized stream of numbers w/ IntStream.collect()
        // IntStream.collect() takes three arguments
        // an Instance of Supplier, instance of ObjIntConsumer( ACCUMULATOR), instnace
        // of BiConsumer (Combiner)

        Suppler<List<Integer>> supplier = ArrayList::new;
        ObjIntConsumer<List<Integer>> accumulator = Collect::add;

    }
}