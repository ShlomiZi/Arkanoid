import biuoop.DrawSurface;

/**
 * Animation interface.
 *
 * @author Shlomi Zidmi
 */
public interface Animation {
    /**
     * The implementation of this method will deal
     * with one frame animation.
     *
     * @param d the surface to be drawn on
     * @param dt the fps rate change
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Determines whether or not the animation should stop.
     *
     * @return boolean true if animation should stop, false otherwise
     */
    boolean shouldStop();
}