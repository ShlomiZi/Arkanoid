/**
 * This class holds information for a single task.
 *
 * @param <T> the type of task
 */
public interface Task<T> {
    /**
    * This method is for running a certain task.
    *
    * @return null - since the task is <Void>
    */
    T run();
}
