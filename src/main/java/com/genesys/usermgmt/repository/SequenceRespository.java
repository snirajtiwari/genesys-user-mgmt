package com.genesys.usermgmt.repository;

import org.springframework.data.repository.CrudRepository;

import com.genesys.usermgmt.repository.entity.Sequence;

public interface SequenceRespository extends CrudRepository<Sequence, String>
{

}