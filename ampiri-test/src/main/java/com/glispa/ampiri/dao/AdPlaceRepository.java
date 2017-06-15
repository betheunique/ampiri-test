package com.glispa.ampiri.dao;

import com.glispa.ampiri.model.AdPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author abhishekrai
 * @since 08/05/2017
 */
public interface AdPlaceRepository extends JpaRepository<AdPlace, Long> {

    @Query("from AdPlace where id = :id")
    AdPlace findById(@Param("id") String id);
}
