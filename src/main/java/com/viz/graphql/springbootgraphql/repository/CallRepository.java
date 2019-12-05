package com.viz.graphql.springbootgraphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viz.graphql.springbootgraphql.entity.Call;

public interface CallRepository extends JpaRepository<Call, Long>{

}
