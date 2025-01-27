package com.notes.app_notes.domain.users.entity;

import com.notes.app_notes.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class UserFilterPreferences extends BaseEntity {

    @Lob
    private String filtersJson;

    @OneToOne(fetch = FetchType.LAZY)
    private UsersEntity user;
}
