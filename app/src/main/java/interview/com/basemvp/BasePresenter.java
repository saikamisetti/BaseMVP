package interview.com.basemvp;

/**
 * Created by Sai on 11/06/18.
 * A presenter for base views.
 */
public interface BasePresenter<T extends BaseView> {
    /**
     * Make this presenter subscribe to this view. This makes presenter ready to work with this
     * view.
     * To actually start data loading, refer to {@linkplain #load()}
     *
     * @param view View for the presenter to present data.
     */
    void subscribe(T view);

    /**
     * Unsubscribe. This will make presenter unsubscribe from all the resources held.
     * Only {@linkplain #subscribe(BaseView)} will kick start the presenter again.
     */
    void unsubscribe();

    /**
     * Start loading data into view.
     */
    void load();
}
