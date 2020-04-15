package com.genesys.usermgmt.util;

import javax.validation.ValidationException;

import com.genesys.usermgmt.constant.GenesysMessages;
import com.genesys.usermgmt.repository.SequenceRespository;
import com.genesys.usermgmt.repository.entity.Sequence;

/**
 * The Class SequenceGenerator.
 */
public class SequenceGenerator {

	/**
	 * Gets the primary key.
	 * 
	 * @param clazz the clazz
	 * @return the primary key
	 */
	public static Long getPrimaryKey(Class<?> clazz) {
		SequenceRespository sequenceRepo = ApplicationContextUtil.getBean(SequenceRespository.class);
		Sequence sequence = sequenceRepo.findById(clazz.getSimpleName()).orElseThrow(() -> new ValidationException(
				String.format(GenesysMessages.FIELD_DOES_NOT_EXIST_IN_DB, "User Sequence")));
		
		// Return Sequence Id
		Long sequenceId = sequence.getSequenceId();
		sequence.setSequenceId(sequenceId + 1);
		sequenceRepo.save(sequence);
		return sequenceId;
	}
}
