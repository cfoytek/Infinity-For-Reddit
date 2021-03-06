package ml.docilealligator.infinityforreddit.AsyncTask;

import android.os.AsyncTask;

import ml.docilealligator.infinityforreddit.FetchUserData;
import ml.docilealligator.infinityforreddit.User.UserDao;
import ml.docilealligator.infinityforreddit.User.UserData;
import retrofit2.Retrofit;

public class LoadUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
    private UserDao userDao;
    private String userName;
    private String iconImageUrl;
    private boolean hasUserInDb;
    private Retrofit retrofit;
    private LoadUserDataAsyncTaskListener loadUserDataAsyncTaskListener;
    public LoadUserDataAsyncTask(UserDao userDao, String userName, Retrofit retrofit, LoadUserDataAsyncTaskListener loadUserDataAsyncTaskListener) {
        this.userDao = userDao;
        this.userName = userName;
        this.retrofit = retrofit;
        this.loadUserDataAsyncTaskListener = loadUserDataAsyncTaskListener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (userDao.getUserData(userName) != null) {
            iconImageUrl = userDao.getUserData(userName).getIconUrl();
            hasUserInDb = true;
        } else {
            hasUserInDb = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (hasUserInDb) {
            loadUserDataAsyncTaskListener.loadUserDataSuccess(iconImageUrl);
        } else {
            FetchUserData.fetchUserData(retrofit, userName, new FetchUserData.FetchUserDataListener() {
                @Override
                public void onFetchUserDataSuccess(UserData userData) {
                    new InsertUserDataAsyncTask(userDao, userData, () -> loadUserDataAsyncTaskListener.loadUserDataSuccess(userData.getIconUrl())).execute();
                }

                @Override
                public void onFetchUserDataFailed() {
                    loadUserDataAsyncTaskListener.loadUserDataSuccess(null);
                }
            });
        }
    }

    public interface LoadUserDataAsyncTaskListener {
        void loadUserDataSuccess(String iconImageUrl);
    }
}
