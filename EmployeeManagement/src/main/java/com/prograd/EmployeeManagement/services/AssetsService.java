package com.prograd.EmployeeManagement.services;

import com.prograd.EmployeeManagement.models.Assets;

import java.util.List;

public interface AssetsService {
    Assets saveAssets(Assets assets);
    List<Assets> getAllAssets();
    Assets getAssetsById(int id);
    Assets updateAssets(Assets assets,int id);
    void deleteAssets(int id);
}
