package org.acosta.cacheexample.services;

import org.acosta.cacheexample.dtos.DtoRequest;
import org.acosta.cacheexample.dtos.DtoResponse;

public interface ICacheData {
	
	DtoResponse  getDataCache(int id);
	DtoResponse update(DtoRequest dtoRequest);
	void flushCache();
}
