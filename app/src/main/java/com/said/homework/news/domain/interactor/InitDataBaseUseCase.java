package com.mint.user_management.domain.interactor;

import com.mint.base.domain.interactor.UseCase;
import com.mint.user_management.domain.repository.UserManagementRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class InitDataBaseUseCase extends UseCase<Boolean, Long> {
    private UserManagementRepository mUserManagementRepository;

    @Inject
    public InitDataBaseUseCase(UserManagementRepository userManagementRepository) {
        this.mUserManagementRepository = userManagementRepository;
    }

    @Override
    public Observable<Boolean> build(Long userCloudID) {
        return mUserManagementRepository.createDatabase(userCloudID);
    }
}