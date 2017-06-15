package com.glispa.ampiri.dao;

import com.glispa.ampiri.model.AdDimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author abhishekrai
 * @since 08/05/2017
 */
@Repository
public interface AdDimensionRepository extends JpaRepository<AdDimension, Long> {

    @Query("from AdDimension where id = :id")
    AdDimension findById(@Param("id") int id);

    @Override
    void delete(AdDimension adDimension);
}
