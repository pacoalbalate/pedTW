package tw.modelo.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.modelo.dao.IAuxOpcionesDao;
import tw.modelo.entidades.AuxOpciones;
import tw.modelo.servicios.IAuxOpcionesService;

/**
 * Implementa el interfaz Façade - Opciones
 * 
 * Redirige las peticiones a los métodos del DAO
 * 
 */
@Service
public class AuxOpcionesImpl implements IAuxOpcionesService {

	@Autowired
	private IAuxOpcionesDao auxOpcionesDao;

	@Override
	@Transactional(readOnly = true)
	public AuxOpciones findById(Long id) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findById(id).orElse(null);
	}

	/**
	 * Busca por Id de opción
	 * @param el id
	 * @return DAO Opciones
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public AuxOpciones findById(String id) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findById(Long.parseLong(id)).orElse(null);
	}
	/**
	 * Devuelve lista de opciones ordenadas por el id
	 * @param tipo de opcion
	 * @return list de Dao opciones
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AuxOpciones> findByTipoOrderById(String tipo) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findByTipoOrderById(tipo);
	}
	
	/**
	 * Devuelve opcion por tipo y nombre
	 * @param tipo
	 * @param opcion
	 * @return DAO Opciones
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public AuxOpciones findByTipoAndOpcion(String tipo, String opcion) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findByTipoAndOpcion(tipo, opcion);
	}

	/**
	 * Devuelve lista de opciones sin ordenar
	 * @param tipo de opcion
	 * @return list de Dao opciones
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AuxOpciones> findByTipoNotOrderById(String tipo) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findByTipoNotOrderById(tipo); 
	}
	
	/**
	 * Devuelve lista de opciones ordenadas por el id
	 *  que contengan el tipo
	 * @param tipo de opcion
	 * @return list de Dao opciones
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AuxOpciones> findByTipoContainingOrderById(String tipo) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findByTipoContainingOrderById(tipo);
	}


}
