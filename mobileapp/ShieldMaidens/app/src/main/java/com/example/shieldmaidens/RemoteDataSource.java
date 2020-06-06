package com.example.shieldmaidens;

import retrofit2.Retrofit;

public class RemoteDataSource {
    private static RemoteDataSource mRemoteDataSource;
    private Retrofit mRestClient;

    private RemoteDataSource() {
    }

    public static RemoteDataSource getInstance() {
        if (mRemoteDataSource == null) {
            mRemoteDataSource = new RemoteDataSource();
        }
        return mRemoteDataSource;
    }

    public <T> T createApiService(Class<T> apiInterface) {
        return RestClient.getClient().create(apiInterface);
    }

    public <T> T createApiService2(Class<T> apiInterface) {
        return RestClient.getClient2().create(apiInterface);
    }

    public <T> T createApiService3(Class<T> apiInterface) {
        return RestClient.getClient3().create(apiInterface);
    }


}
