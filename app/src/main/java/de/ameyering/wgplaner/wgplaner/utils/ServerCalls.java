package de.ameyering.wgplaner.wgplaner.utils;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import io.swagger.client.ApiCallback;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.api.GroupApi;
import io.swagger.client.api.ShoppinglistApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.Configuration;
import io.swagger.client.model.Group;
import io.swagger.client.model.GroupCode;
import io.swagger.client.model.ListItem;
import io.swagger.client.model.ShoppingList;
import io.swagger.client.model.SuccessResponse;
import io.swagger.client.model.User;

public class ServerCalls implements ServerCallsInterface {
    private static final String USER_ID_AUTH_LABEL = "UserIDAuth";
    private static final String FIREBASE_ID_AUTH_LABEL = "FirebaseIDAuth";

    private static final String BASE_URL = "https://api.wgplaner.ameyering.de";

    private static final String SERVER_CONNECTION_SUCCEEDED_TAG = "ServerConnectionSuccess";
    private static final String SERVER_CONNECTION_FAILED_TAG = "ServerConnectionFail";

    private static final String ASYNCHRONOUS_FLAG = ":Asynchronous";
    private static final String WAIT_FOR_RESULT_FLAG = ":WaitForResult";

    private static final String CREATE_USER_NAME = "CreateUser";
    private static final String UPDATE_USER_NAME = "UpdateUser";
    private static final String GET_USER_NAME = "GetUser";
    private static final String CREATE_GROUP_NAME = "CreateGroup";
    private static final String GET_GROUP_NAME = "GetGroup";
    private static final String JOIN_GROUP_NAME = "JoinGroup";
    private static final String GET_SHOPPING_LIST_NAME = "GetShoppingList";
    private static final String CREATE_SHOPPING_LIST_ITEM_NAME = "CreateShoppingListItem";

    private static ApiClient client;

    private static ServerCalls singleton;

    private static DataProvider dataProvider = DataProvider.getInstance();

    static {
        singleton = new ServerCalls();
        client = Configuration.getDefaultApiClient();
        client.setBasePath(BASE_URL);
    }

    private static boolean setAuth(String method) {
        if (method != null && !method.isEmpty()) {
            ApiKeyAuth auth = (ApiKeyAuth) client.getAuthentication(method);
            auth.setApiKey(dataProvider.getCurrentUserUid());
            return true;
        }

        return false;
    }

    public static ServerCallsInterface getInstance() {
        return singleton;
    }

    @Override
    public void createUserAsync(User user, @Nullable final OnAsyncCallListener<User> listener) {
        setAuth(USER_ID_AUTH_LABEL);

        try {
            UserApi api = new UserApi();
            api.createUserAsync(user, new ApiCallback<User>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    logError(CREATE_USER_NAME, ASYNCHRONOUS_FLAG);

                    if (listener != null) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(User result, int statusCode, Map<String, List<String>> responseHeaders) {
                    logSuccess(CREATE_USER_NAME, ASYNCHRONOUS_FLAG);

                    if (result != null) {

                        if (listener != null) {
                            listener.onSuccess(result);
                        }
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });

        } catch (ApiException e) {
            if (listener != null) {
                logError(CREATE_USER_NAME, ASYNCHRONOUS_FLAG);
                listener.onFailure(e);
            }
        }
    }

    public ApiResponse<User> createUser(User user) {
        if (user != null) {
            CreateUser task = new CreateUser();

            try {
                return task.execute(user).get();

            } catch (ExecutionException e) {
                logError(CREATE_USER_NAME, WAIT_FOR_RESULT_FLAG);
                return null;

            } catch (InterruptedException e) {
                logError(CREATE_USER_NAME, WAIT_FOR_RESULT_FLAG);
                return null;
            }
        }

        return null;
    }

    public void getUserAsync(String uid, @Nullable final OnAsyncCallListener<User> listener) {
        setAuth(FIREBASE_ID_AUTH_LABEL);

        try {
            UserApi api = new UserApi();
            api.getUserAsync(uid, new ApiCallback<User>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    logError(GET_USER_NAME, ASYNCHRONOUS_FLAG);

                    if (listener !=  null) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(User result, int statusCode, Map<String, List<String>> responseHeaders) {
                    logSuccess(GET_USER_NAME, ASYNCHRONOUS_FLAG);

                    if (result != null && listener != null) {
                        listener.onSuccess(result);
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });

        } catch (ApiException e) {
            if (listener != null) {
                logError(GET_USER_NAME, ASYNCHRONOUS_FLAG);
                listener.onFailure(e);
            }
        }
    }

    public ApiResponse<User> getUser(String uid) {
        if (uid != null) {
            GetUser task = new GetUser();

            try {
                return task.execute(uid).get();

            } catch (ExecutionException e) {
                logError(GET_USER_NAME, WAIT_FOR_RESULT_FLAG);
                return null;

            } catch (InterruptedException e) {
                logError(GET_USER_NAME, WAIT_FOR_RESULT_FLAG);
                return null;
            }
        }

        return null;
    }

    public void updateUserAsync(User user, @Nullable final OnAsyncCallListener<User> listener) {
        setAuth(USER_ID_AUTH_LABEL);

        try {
            UserApi api = new UserApi();
            api.updateUserAsync(user, new ApiCallback<User>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    logError(UPDATE_USER_NAME, ASYNCHRONOUS_FLAG);

                    if (listener != null) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(User result, int statusCode, Map<String, List<String>> responseHeaders) {
                    logError(UPDATE_USER_NAME, ASYNCHRONOUS_FLAG);

                    if (result != null && listener !=  null) {
                        listener.onSuccess(result);
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });

        } catch (ApiException e) {
            if (listener != null) {
                logError(UPDATE_USER_NAME, ASYNCHRONOUS_FLAG);
                listener.onFailure(e);
            }
        }
    }

    public ApiResponse<User> updateUser(User user) {
        if (user != null) {
            UpdateUser task = new UpdateUser();

            try {
                return task.execute(user).get();

            } catch (ExecutionException e) {
                logError(UPDATE_USER_NAME, WAIT_FOR_RESULT_FLAG);
                return null;

            } catch (InterruptedException e) {
                logError(UPDATE_USER_NAME, WAIT_FOR_RESULT_FLAG);
                return null;
            }
        }

        return null;
    }

    @Override
    public void getUserImageAsync(String uid, @Nullable final OnAsyncCallListener<byte[]> listener) {
        if (uid != null) {
            setAuth(USER_ID_AUTH_LABEL);

            try {
                UserApi api = new UserApi();
                api.getUserImageAsync(uid, new ApiCallback<byte[]>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onFailure(e);
                        }
                    }

                    @Override
                    public void onSuccess(byte[] result, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onSuccess(result);
                        }
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });

            } catch (ApiException e) {
                if (listener != null) {
                    listener.onFailure(e);
                }
            }
        }
    }

    @Override
    public ApiResponse<byte[]> getUserImage(String uid) {
        if (uid != null) {
            GetUserImage task = new GetUserImage();

            try {
                return task.execute(uid).get();

            } catch (ExecutionException e) {
                return null;

            } catch (InterruptedException e) {
                return null;
            }
        }

        return null;
    }

    public void updateUserImageAsync(File image,
        @Nullable final OnAsyncCallListener<SuccessResponse> listener) {
        if (image != null) {
            setAuth(USER_ID_AUTH_LABEL);

            try {
                UserApi api = new UserApi();
                api.updateUserImageAsync(dataProvider.getCurrentUserUid(), image,
                new ApiCallback<SuccessResponse>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onFailure(e);
                        }
                    }

                    @Override
                    public void onSuccess(SuccessResponse result, int statusCode,
                        Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onSuccess(result);
                        }
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });

            } catch (ApiException e) {
                if (listener != null) {
                    listener.onFailure(e);
                }
            }
        }
    }

    public ApiResponse<SuccessResponse> updateUserImage(File image) {
        if (image != null) {
            UpdateUserImage task = new UpdateUserImage();

            try {
                return task.execute(image).get();

            } catch (ExecutionException e) {
                return null;

            } catch (InterruptedException e) {
                return null;
            }
        }

        return null;
    }

    public void leaveGroupAsync(@Nullable final OnAsyncCallListener<SuccessResponse> listener) {
        setAuth(USER_ID_AUTH_LABEL);

        try {
            GroupApi api = new GroupApi();
            api.leaveGroupAsync(new ApiCallback<SuccessResponse>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    if (listener != null) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(SuccessResponse result, int statusCode,
                    Map<String, List<String>> responseHeaders) {
                    if (listener != null) {
                        listener.onSuccess(result);
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });

        } catch (ApiException e) {
            if (listener != null) {
                listener.onFailure(e);
            }
        }
    }

    public ApiResponse<SuccessResponse> leaveGroup() {
        LeaveGroup task = new LeaveGroup();

        try {
            return task.execute().get();

        } catch (ExecutionException e) {
            return null;

        } catch (InterruptedException e) {
            return null;
        }
    }

    public void getGroupAsync(UUID groupUid,
        @Nullable final OnAsyncCallListener<Group> listener) {
        setAuth(USER_ID_AUTH_LABEL);

        try {
            GroupApi api = new GroupApi();
            api.getGroupAsync(groupUid, new ApiCallback<Group>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    logError(GET_GROUP_NAME, ASYNCHRONOUS_FLAG);

                    if (listener != null) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(Group result, int statusCode, Map<String, List<String>> responseHeaders) {
                    logSuccess(GET_GROUP_NAME, ASYNCHRONOUS_FLAG);

                    if (result != null && listener != null) {
                        listener.onSuccess(result);
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });

        } catch (ApiException e) {
            logError(GET_GROUP_NAME, ASYNCHRONOUS_FLAG);

            if (listener != null) {
                listener.onFailure(e);
            }
        }
    }

    public ApiResponse<Group> getGroup(UUID groupUid) {
        if (groupUid != null) {
            GetGroup task = new GetGroup();

            try {
                return task.execute(groupUid).get();

            } catch (ExecutionException e) {
                logError(GET_GROUP_NAME, WAIT_FOR_RESULT_FLAG);
                return null;

            } catch (InterruptedException e) {
                logError(GET_GROUP_NAME, WAIT_FOR_RESULT_FLAG);
                return null;
            }
        }

        return null;
    }

    @Override
    public void updateGroupAsync(Group group, @Nullable final OnAsyncCallListener<Group> listener) {
        if (group != null) {
            setAuth(USER_ID_AUTH_LABEL);

            try {
                GroupApi api = new GroupApi();
                api.updateGroupAsync(group, new ApiCallback<Group>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onFailure(e);
                        }
                    }

                    @Override
                    public void onSuccess(Group result, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onSuccess(result);
                        }
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });

            } catch (ApiException e) {
                if (listener != null) {
                    listener.onFailure(e);
                }
            }
        }
    }

    @Override
    public ApiResponse<Group> updateGroup(Group group) {
        if (group != null) {
            UpdateGroup task = new UpdateGroup();

            try {
                return task.execute(group).get();

            } catch (ExecutionException e) {
                return null;

            } catch (InterruptedException e) {
                return null;
            }
        }

        return null;
    }

    public void createGroupAsync(Group group,
        @Nullable final OnAsyncCallListener<Group> listener) {
        setAuth(USER_ID_AUTH_LABEL);

        try {
            GroupApi api = new GroupApi();
            api.createGroupAsync(group, new ApiCallback<Group>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    logError(CREATE_GROUP_NAME, ASYNCHRONOUS_FLAG);

                    if (listener != null) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(Group result, int statusCode, Map<String, List<String>> responseHeaders) {
                    logSuccess(CREATE_GROUP_NAME, ASYNCHRONOUS_FLAG);

                    if (result != null && listener != null) {
                        listener.onSuccess(result);
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });

        } catch (ApiException e) {
            logError(CREATE_GROUP_NAME, ASYNCHRONOUS_FLAG);

            if (listener != null) {
                listener.onFailure(e);
            }
        }
    }

    public ApiResponse<Group> createGroup(Group group) {
        if (group != null) {
            setAuth(USER_ID_AUTH_LABEL);
            CreateGroup task = new CreateGroup();

            try {
                return task.execute(group).get();

            } catch (ExecutionException e) {
                logError(CREATE_GROUP_NAME, WAIT_FOR_RESULT_FLAG);
                return null;

            } catch (InterruptedException e) {
                logError(CREATE_GROUP_NAME, WAIT_FOR_RESULT_FLAG);
                return null;
            }
        }

        return null;
    }

    public void joinGroupAsync(String accessKey,
        @Nullable final OnAsyncCallListener<Group> listener) {
        setAuth(USER_ID_AUTH_LABEL);

        try {
            GroupApi api = new GroupApi();
            api.joinGroupAsync(accessKey, new ApiCallback<Group>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    logError(JOIN_GROUP_NAME, ASYNCHRONOUS_FLAG);

                    if (listener != null) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(Group result, int statusCode, Map<String, List<String>> responseHeaders) {
                    logSuccess(JOIN_GROUP_NAME, ASYNCHRONOUS_FLAG);

                    if (result != null && listener != null) {
                        listener.onSuccess(result);
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });

        } catch (ApiException e) {
            logError(JOIN_GROUP_NAME, ASYNCHRONOUS_FLAG);

            if (listener != null) {
                listener.onFailure(e);
            }
        }
    }

    public ApiResponse<Group> joinGroup(String accessKey) {
        if (accessKey != null) {
            JoinGroup task = new JoinGroup();

            try {
                return task.execute(accessKey).get();

            } catch (ExecutionException e) {
                logError(JOIN_GROUP_NAME, WAIT_FOR_RESULT_FLAG);
                return null;

            } catch (InterruptedException e) {
                logError(JOIN_GROUP_NAME, WAIT_FOR_RESULT_FLAG);
                return null;
            }
        }

        return null;
    }

    @Override
    public void createGroupKeyAsync(@Nullable final OnAsyncCallListener<GroupCode> listener) {
        setAuth(USER_ID_AUTH_LABEL);

        GroupApi api = new GroupApi();

        try {
            api.createGroupCodeAsync(dataProvider.getCurrentGroupUID(),
            new ApiCallback<GroupCode>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    if (listener != null) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(GroupCode result, int statusCode, Map<String, List<String>> responseHeaders) {
                    if (listener != null) {
                        listener.onSuccess(result);
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });

        } catch (ApiException e) {
            if (listener != null) {
                listener.onFailure(e);
            }
        }
    }

    @Override
    public ApiResponse<GroupCode> createGroupKey() {
        try {
            CreateGroupKey task = new CreateGroupKey();
            return task.execute().get();

        } catch (ExecutionException e) {
            return null;

        } catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    public void getGroupImageAsync(@Nullable final OnAsyncCallListener<byte[]> listener) {
        setAuth(USER_ID_AUTH_LABEL);

        try {
            GroupApi api = new GroupApi();
            api.getGroupImageAsync(dataProvider.getCurrentGroupUID(), new ApiCallback<byte[]>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    if (listener != null) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(byte[] result, int statusCode, Map<String, List<String>> responseHeaders) {
                    if (listener != null) {
                        listener.onSuccess(result);
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });

        } catch (ApiException e) {
            if (listener != null) {
                listener.onFailure(e);
            }
        }
    }

    @Override
    public ApiResponse<byte[]> getGroupImage() {
        try {
            GetGroupImage task = new GetGroupImage();
            return task.execute().get();

        } catch (ExecutionException e) {
            return null;

        } catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    public void updateGroupImageAsync(File image,
        @Nullable final OnAsyncCallListener<SuccessResponse> listener) {
        if (image != null) {
            setAuth(USER_ID_AUTH_LABEL);

            GroupApi api = new GroupApi();

            try {
                api.updateGroupImageAsync(dataProvider.getCurrentGroupUID(), image,
                new ApiCallback<SuccessResponse>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onFailure(e);
                        }
                    }

                    @Override
                    public void onSuccess(SuccessResponse result, int statusCode,
                        Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onSuccess(result);
                        }
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });

            } catch (ApiException e) {
                if (listener != null) {
                    listener.onFailure(e);
                }
            }
        }
    }

    @Override
    public ApiResponse<SuccessResponse> updateGroupImage(File image) {
        if (image != null) {
            setAuth(USER_ID_AUTH_LABEL);

            try {
                UpdateGroupImage task = new UpdateGroupImage();
                return task.execute(image).get();

            } catch (ExecutionException e) {
                return null;

            } catch (InterruptedException e) {
                return null;
            }
        }

        return null;
    }

    public void getShoppingListAsync(@Nullable final OnAsyncCallListener<ShoppingList>
        listener) {
        setAuth(FIREBASE_ID_AUTH_LABEL);

        try {
            ShoppinglistApi api = new ShoppinglistApi();
            api.getListItemsAsync(dataProvider.getCurrentGroupUID(),
            new ApiCallback<ShoppingList>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    logError(GET_SHOPPING_LIST_NAME, ASYNCHRONOUS_FLAG);

                    if (listener != null) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(ShoppingList result, int statusCode,
                    Map<String, List<String>> responseHeaders) {
                    logSuccess(GET_SHOPPING_LIST_NAME, ASYNCHRONOUS_FLAG);

                    if (result != null && listener != null) {
                        listener.onSuccess(result);
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });

        } catch (ApiException e) {
            logError(GET_SHOPPING_LIST_NAME, ASYNCHRONOUS_FLAG);

            if (listener != null) {
                listener.onFailure(e);
            }
        }
    }

    public ApiResponse<ShoppingList> getShoppingList() {
        if (dataProvider.getCurrentGroupUID() != null) {
            GetShoppingList task = new GetShoppingList();

            try {
                return task.execute(dataProvider.getCurrentGroupUID()).get();

            } catch (ExecutionException e) {
                logError(GET_SHOPPING_LIST_NAME, WAIT_FOR_RESULT_FLAG);
                return null;

            } catch (InterruptedException e) {
                logError(GET_SHOPPING_LIST_NAME, WAIT_FOR_RESULT_FLAG);
                return null;
            }
        }

        return null;
    }

    public void createShoppingListItemAsync(ListItem item,
        @Nullable final OnAsyncCallListener<ListItem> listener) {
        setAuth(FIREBASE_ID_AUTH_LABEL);

        try {
            ShoppinglistApi api = new ShoppinglistApi();
            api.createListItemAsync(dataProvider.getCurrentGroupUID(), item,
            new ApiCallback<ListItem>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    logError(CREATE_SHOPPING_LIST_ITEM_NAME, ASYNCHRONOUS_FLAG);

                    if (listener != null) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(ListItem result, int statusCode, Map<String, List<String>> responseHeaders) {
                    logSuccess(CREATE_SHOPPING_LIST_ITEM_NAME, ASYNCHRONOUS_FLAG);

                    if (result != null && listener != null) {
                        listener.onSuccess(result);
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });

        } catch (ApiException e) {
            logError(CREATE_SHOPPING_LIST_ITEM_NAME, ASYNCHRONOUS_FLAG);

            if (listener != null) {
                listener.onFailure(e);
            }
        }
    }

    public ApiResponse<ListItem> createShoppingListItem(ListItem item) {
        if (item != null) {
            CreateShoppingListItem task = new CreateShoppingListItem();

            try {
                return task.execute(item).get();

            } catch (ExecutionException e) {
                logError(CREATE_SHOPPING_LIST_ITEM_NAME, WAIT_FOR_RESULT_FLAG);
                return null;

            } catch (InterruptedException e) {
                logError(CREATE_SHOPPING_LIST_ITEM_NAME, WAIT_FOR_RESULT_FLAG);
                return null;
            }
        }

        return null;
    }

    @Override
    public void updateShoppingListItemAsync(ListItem item,
        @Nullable final OnAsyncCallListener<ListItem> listener) {
        if (item != null) {
            setAuth(FIREBASE_ID_AUTH_LABEL);

            try {
                ShoppinglistApi api = new ShoppinglistApi();
                api.createListItemAsync(dataProvider.getCurrentGroupUID(), item,
                new ApiCallback<ListItem>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onFailure(e);
                        }
                    }

                    @Override
                    public void onSuccess(ListItem result, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onSuccess(result);
                        }
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });

            } catch (ApiException e) {
                if (listener != null) {
                    listener.onFailure(e);
                }
            }
        }
    }

    @Override
    public ApiResponse<ListItem> updateShoppingListItem(ListItem item) {
        if (item != null) {
            UpdateShoppingListItem task = new UpdateShoppingListItem();

            try {
                return task.execute(item).get();

            } catch (ExecutionException e) {
                return null;

            } catch (InterruptedException e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public void buyListItemsAsync(List<UUID> items,
        @Nullable final OnAsyncCallListener<SuccessResponse> listener) {
        if (items != null) {
            setAuth(USER_ID_AUTH_LABEL);

            ShoppinglistApi api = new ShoppinglistApi();

            try {
                api.buyListItemsAsync(dataProvider.getCurrentGroupUID(), items,
                new ApiCallback<SuccessResponse>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onFailure(e);
                        }
                    }

                    @Override
                    public void onSuccess(SuccessResponse result, int statusCode,
                        Map<String, List<String>> responseHeaders) {
                        if (listener != null) {
                            listener.onSuccess(result);
                        }
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });

            } catch (ApiException e) {
                if (listener != null) {
                    listener.onFailure(e);
                }
            }
        }
    }

    @Override
    public ApiResponse<SuccessResponse> buyListItems(List<UUID> items) {
        if (items != null) {
            setAuth(USER_ID_AUTH_LABEL);

            try {
                BuyListItems task = new BuyListItems();
                return task.execute(items).get();

            } catch (ExecutionException e) {
                return null;

            } catch (InterruptedException e) {
                return null;
            }
        }

        return null;
    }

    private static void logError(String name, String method) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(name).append(method);
        Log.e(SERVER_CONNECTION_FAILED_TAG, buffer.toString());
    }

    private static void logSuccess(String name, String method) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(name).append(method);
        Log.i(SERVER_CONNECTION_SUCCEEDED_TAG, buffer.toString());
    }

    private static class CreateUser extends AsyncTask<User, Void, ApiResponse<User>> {

        @Override
        protected ApiResponse<User> doInBackground(User... users) {
            if (users != null && users.length > 0) {
                try {
                    setAuth(USER_ID_AUTH_LABEL);
                    UserApi api = new UserApi();

                    return api.createUserWithHttpInfo(users[0]);

                } catch (ApiException e) {
                    logError(CREATE_USER_NAME, WAIT_FOR_RESULT_FLAG);
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class GetUser extends AsyncTask<String, Void, ApiResponse<User>> {

        @Override
        protected ApiResponse<User> doInBackground(String... strings) {
            if (strings != null && strings.length > 0) {
                setAuth(FIREBASE_ID_AUTH_LABEL);
                UserApi api = new UserApi();

                try {
                    return api.getUserWithHttpInfo(strings[0]);

                } catch (ApiException e) {
                    logError(GET_USER_NAME, WAIT_FOR_RESULT_FLAG);
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class UpdateUser extends AsyncTask<User, Void, ApiResponse<User>> {

        @Override
        protected ApiResponse<User> doInBackground(User... users) {
            if (users != null && users.length > 0) {
                setAuth(USER_ID_AUTH_LABEL);
                UserApi api = new UserApi();

                try {
                    return api.updateUserWithHttpInfo(users[0]);

                } catch (ApiException e) {
                    logError(UPDATE_USER_NAME, WAIT_FOR_RESULT_FLAG);
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class GetUserImage extends AsyncTask<String, Void, ApiResponse<byte[]>> {

        @Override
        protected ApiResponse<byte[]> doInBackground(String... strings) {
            if (strings != null && strings.length > 0) {
                setAuth(USER_ID_AUTH_LABEL);
                UserApi api = new UserApi();

                try {
                    return api.getUserImageWithHttpInfo(strings[0]);

                } catch (ApiException e) {
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class UpdateUserImage extends AsyncTask<File, Void, ApiResponse<SuccessResponse>> {

        @Override
        protected ApiResponse<SuccessResponse> doInBackground(File... files) {
            if (files != null && files.length > 0 && files[0] != null) {
                setAuth(USER_ID_AUTH_LABEL);
                UserApi api = new UserApi();

                try {
                    return api.updateUserImageWithHttpInfo(dataProvider.getCurrentUserUid(), files[0]);

                } catch (ApiException e) {
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class LeaveGroup extends AsyncTask<Void, Void, ApiResponse<SuccessResponse>> {

        @Override
        protected ApiResponse<SuccessResponse> doInBackground(Void... voids) {
            setAuth(USER_ID_AUTH_LABEL);
            GroupApi api = new GroupApi();

            try {
                return api.leaveGroupWithHttpInfo();

            } catch (ApiException e) {
                return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
            }
        }
    }

    private static class GetGroup extends AsyncTask<UUID, Void, ApiResponse<Group>> {

        @Override
        protected ApiResponse<Group> doInBackground(UUID... uuids) {
            if (uuids != null && uuids.length > 0) {
                setAuth(USER_ID_AUTH_LABEL);
                GroupApi api = new GroupApi();

                try {
                    return api.getGroupWithHttpInfo(uuids[0]);

                } catch (ApiException e) {
                    logError(GET_GROUP_NAME, WAIT_FOR_RESULT_FLAG);
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class UpdateGroup extends AsyncTask<Group, Void, ApiResponse<Group>> {

        @Override
        protected ApiResponse<Group> doInBackground(Group... groups) {
            if (groups != null && groups.length > 0) {
                setAuth(USER_ID_AUTH_LABEL);
                GroupApi api = new GroupApi();

                try {
                    return api.updateGroupWithHttpInfo(groups[0]);

                } catch (ApiException e) {
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class CreateGroup extends AsyncTask<Group, Void, ApiResponse<Group>> {

        @Override
        protected ApiResponse<Group> doInBackground(Group... groups) {
            if (groups != null && groups.length > 0) {
                setAuth(USER_ID_AUTH_LABEL);
                GroupApi api = new GroupApi();

                try {
                    return api.createGroupWithHttpInfo(groups[0]);

                } catch (ApiException e) {
                    logError(CREATE_GROUP_NAME, WAIT_FOR_RESULT_FLAG);
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class JoinGroup extends AsyncTask<String, Void, ApiResponse<Group>> {

        @Override
        protected ApiResponse<Group> doInBackground(String... strings) {
            if (strings != null && strings.length > 0) {
                setAuth(USER_ID_AUTH_LABEL);
                GroupApi api = new GroupApi();

                try {
                    return api.joinGroupWithHttpInfo(strings[0]);

                } catch (ApiException e) {
                    logError(JOIN_GROUP_NAME, WAIT_FOR_RESULT_FLAG);
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class CreateGroupKey extends AsyncTask<Void, Void, ApiResponse<GroupCode>> {

        @Override
        protected ApiResponse<GroupCode> doInBackground(Void... voids) {
            setAuth(USER_ID_AUTH_LABEL);

            GroupApi api = new GroupApi();

            try {
                return api.createGroupCodeWithHttpInfo(dataProvider.getCurrentGroupUID());

            } catch (ApiException e) {
                return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
            }
        }
    }

    private static class GetGroupImage extends AsyncTask<Void, Void, ApiResponse<byte[]>> {

        @Override
        protected ApiResponse<byte[]> doInBackground(Void... voids) {
            setAuth(USER_ID_AUTH_LABEL);

            GroupApi api = new GroupApi();

            try {
                return api.getGroupImageWithHttpInfo(dataProvider.getCurrentGroupUID());

            } catch (ApiException e) {
                return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
            }
        }
    }

    private static class UpdateGroupImage extends AsyncTask<File, Void, ApiResponse<SuccessResponse>> {

        @Override
        protected ApiResponse<SuccessResponse> doInBackground(File... files) {
            if (files != null && files.length > 0) {
                setAuth(USER_ID_AUTH_LABEL);

                GroupApi api = new GroupApi();

                try {
                    return api.updateGroupImageWithHttpInfo(dataProvider.getCurrentGroupUID(), files[0]);

                } catch (ApiException e) {
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class GetShoppingList extends AsyncTask<UUID, Void, ApiResponse<ShoppingList>> {

        @Override
        protected ApiResponse<ShoppingList> doInBackground(UUID... uuids) {
            if (uuids != null && uuids.length > 0) {
                setAuth(FIREBASE_ID_AUTH_LABEL);
                ShoppinglistApi api = new ShoppinglistApi();

                try {
                    return api.getListItemsWithHttpInfo(uuids[0]);

                } catch (ApiException e) {
                    logError(GET_SHOPPING_LIST_NAME, WAIT_FOR_RESULT_FLAG);
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class CreateShoppingListItem extends
        AsyncTask<ListItem, Void, ApiResponse<ListItem>> {

        @Override
        protected ApiResponse<ListItem> doInBackground(ListItem... listItems) {
            if (listItems != null && listItems.length > 0) {
                setAuth(FIREBASE_ID_AUTH_LABEL);
                ShoppinglistApi api = new ShoppinglistApi();

                try {
                    return api.createListItemWithHttpInfo(dataProvider.getCurrentGroupUID(),
                            listItems[0]);

                } catch (ApiException e) {
                    logError(CREATE_SHOPPING_LIST_ITEM_NAME, WAIT_FOR_RESULT_FLAG);
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class UpdateShoppingListItem extends
        AsyncTask<ListItem, Void, ApiResponse<ListItem>> {

        @Override
        protected ApiResponse<ListItem> doInBackground(ListItem... listItems) {
            if (listItems != null && listItems.length > 0) {
                setAuth(FIREBASE_ID_AUTH_LABEL);
                ShoppinglistApi api = new ShoppinglistApi();

                try {
                    return api.updateListItemWithHttpInfo(dataProvider.getCurrentGroupUID(),
                            listItems[0]);

                } catch (ApiException e) {
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }

    private static class BuyListItems extends
        AsyncTask<List<UUID>, Void, ApiResponse<SuccessResponse>> {

        @Override
        protected ApiResponse<SuccessResponse> doInBackground(List<UUID>[] lists) {
            if (lists != null && lists.length > 0) {
                setAuth(USER_ID_AUTH_LABEL);

                ShoppinglistApi api = new ShoppinglistApi();

                try {
                    return api.buyListItemsWithHttpInfo(dataProvider.getCurrentGroupUID(), lists[0]);

                } catch (ApiException e) {
                    return new ApiResponse<>(e.getCode(), e.getResponseHeaders(), null);
                }
            }

            return null;
        }
    }
}
