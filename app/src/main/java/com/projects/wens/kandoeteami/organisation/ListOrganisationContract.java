package com.projects.wens.kandoeteami.organisation;

import android.support.annotation.NonNull;

import com.projects.wens.kandoeteami.organisation.data.Organisation;

import java.util.List;

/**
 * Created by senne on 21/02/2016.
 */
public interface ListOrganisationContract {
    interface view{
        void setProgressIndicator(boolean active);
        void showOrganisations(List<Organisation> organisations);
        void showOrganisationDetailUi(Integer noteId);
        void showErrorMessage(int status);
    }

    interface UserActionListener{
        void loadOrga(boolean forceUpdate);
        void openOrganisationThema(@NonNull Organisation requestOrga);
    }
}
