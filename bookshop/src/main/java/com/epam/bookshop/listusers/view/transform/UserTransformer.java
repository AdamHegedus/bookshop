package com.epam.bookshop.listusers.view.transform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import com.epam.bookshop.listusers.view.model.UserSummaryView;
import com.epam.bookshop.user.domain.User;

@Component
public class UserTransformer {
    @Autowired
    public UserTransformer(ConversionService conversionService) {
        super();
    }

    public List<UserSummaryView> transformUsersToSummaries(List<User> users) {
        List<UserSummaryView> result = new ArrayList<UserSummaryView>();
        for (User user : users) {
            result.add(transformUserToSummary(user));
        }
        return result;
    }

    public UserSummaryView transformUserToSummary(User user) {
        UserSummaryView result = new UserSummaryView();
        result.setEmail(user.getEmail());
        result.setDateOfBirth(user.getDateOfBirth());
        result.setUserId(user.getUserId());
        return result;
    }

    //    public UserDetailsView transformUserToDetails(User user) {
    //        UserDetailsView result = new UserDetailsView();
    //        result.setSynopsis(user.getSynopsis());
    //        return result;
    //    }
}
