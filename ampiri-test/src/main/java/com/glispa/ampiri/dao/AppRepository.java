package com.glispa.ampiri.dao;

import com.glispa.ampiri.model.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author abhishekrai
 * @since 08/05/2017
 */
public interface AppRepository extends JpaRepository<App, Long> {

    @Query("from App where id = :id")
    App findById(@Param("id") String id);

}
