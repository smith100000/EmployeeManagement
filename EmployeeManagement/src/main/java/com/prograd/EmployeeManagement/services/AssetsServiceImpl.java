package com.prograd.EmployeeManagement.services;

import com.prograd.EmployeeManagement.models.Assets;
import com.prograd.EmployeeManagement.repository.AssetsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetsServiceImpl implements AssetsService {
    @Autowired
    private AssetsRepository assetsRepository;
    public AssetsServiceImpl(AssetsRepository assetsRepository){
        this.assetsRepository=assetsRepository;
    }
    @Override
    public Assets saveAssets(Assets assets) {
        return assetsRepository.save(assets);
    }

    @Override
    public List<Assets> getAllAssets() {
        return assetsRepository.findAll();
    }

    @Override
    public Assets getAssetsById(int id) {
        return assetsRepository.findById(id).orElseThrow();
    }

    @Override
    public Assets updateAssets(Assets assets, int id) {
        Assets existingAssets=assetsRepository.findById(id).orElseThrow();
        existingAssets.setAssetsName(assets.getAssetsName());
        existingAssets.setAssetsType(assets.getAssetsType());
        existingAssets.setAssetsValue(assets.getAssetsValue());
        assetsRepository.save(existingAssets);
        return existingAssets;
    }

    @Override
    public void  deleteAssets(int id) {
        assetsRepository.findById(id);
        assetsRepository.deleteById(id);
    }
}
