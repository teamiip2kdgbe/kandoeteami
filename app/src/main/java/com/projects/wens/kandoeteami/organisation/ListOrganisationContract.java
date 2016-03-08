package com.projects.wens.kandoeteami.organisation;

import android.support.annotation.NonNull;

import com.projects.wens.kandoeteami.organisation.data.OrganisationList;

import java.util.List;

/**
 * Created by senne on 21/02/2016.
 */
public interface ListOrganisationContract {
    interface view{
        void setProgressIndicator(boolean active);
        void showOrganisations(List<OrganisationList> organisations);
        void showOrganisationDetailUi(Integer noteId);
        void showErrorMessage(int status);
    }

    interface UserActionListener{
        void loadOrganisations(boolean forceUpdate, String token);
        void openOrganisationThema(@NonNull OrganisationList requestOrga);
    }
}
