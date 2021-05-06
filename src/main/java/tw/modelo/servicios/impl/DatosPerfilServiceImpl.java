/**
 * 
 */
package tw.modelo.servicios.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.modelo.dao.IDatosPerfilDao;
import tw.modelo.entidades.DatosPerfil;
import tw.modelo.servicios.IDatosPerfilService;

/**
 * @author Portatil
 * Para implementar el patron Fachada hacia el acceso a datos
 */
@Service
public class DatosPerfilServiceImpl implements IDatosPerfilService {

	@Autowired
	private IDatosPerfilDao  datosperfilDao;

	@Override
	@Transactional
	public void save(DatosPerfil datosperfil) {
		// TODO Auto-generated method stub
		datosperfilDao.save(datosperfil);
	}

	@Override
	@Transactional(readOnly = true)
	public DatosPerfil findById(Long id) {
		// TODO Auto-generated method stub
		return datosperfilDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		datosperfilDao.deleteById(id);
	}
	


	@Override
	@Transactional(readOnly = true)
	public Page<Object> findByIdInRegionWithKeywordDistint(Pageable pageable, List<Long> regiones, String keyword, Date desde, Date hasta) {
		// TODO Auto-generated method stub
		if (regiones == null) {
			return datosperfilDao.findAllWithKeywordDistint(pageable, keyword, desde, hasta);
		} else {
			return datosperfilDao.findByIdInRegionWithKeywordDistint(pageable, regiones, keyword, desde, hasta);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Object> findByIdInCentroWithKeywordDistint(Pageable pageable, String keyword, List<Long> centros, Date desde, Date hasta) {
		// TODO Auto-generated method stub
		if (centros == null) {
			return datosperfilDao.findAllWithKeywordDistint(pageable, keyword, desde, hasta);
		} else {
			return datosperfilDao.findByIdInCentroWithKeywordDistint(pageable, keyword, centros, desde, hasta);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Object> findByIdInDatoWithKeywordDistint(Pageable pageable, List<String> datos, String keyword, Date desde, Date hasta) {
		// TODO Auto-generated method stub
		if (datos == null) {
			return datosperfilDao.findAllWithKeywordDistint(pageable, keyword, desde, hasta);
		} else {
			return datosperfilDao.findByIdInDatoWithKeywordDistint(pageable, datos, keyword, desde, hasta);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Object> findByIdInRegionDatoWithKeywordDistint(Pageable pageable, List<Long> regiones,
			String keyword, List<String> datos, Date desde, Date hasta) {
		// TODO Auto-generated method stub
		if (datos == null) {
			return findByIdInRegionWithKeywordDistint(pageable, regiones, keyword, desde, hasta);
		} else if (regiones == null) {
			return findByIdInDatoWithKeywordDistint(pageable, datos, keyword, desde, hasta);
		} else {
			return datosperfilDao.findByIdInRegionDatoWithKeywordDistint(pageable, regiones, keyword, datos, desde, hasta);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Object> findByIdInCentroDatoWithKeywordDistint(Pageable pageable, List<Long> centros,
			String keyword, List<String> datos, Date desde, Date hasta) {
		// TODO Auto-generated method stub
		if (datos == null) {
			return findByIdInCentroWithKeywordDistint(pageable, keyword, centros, desde, hasta);
		} else if (centros == null) {
			return findByIdInDatoWithKeywordDistint(pageable, datos, keyword, desde, hasta);
		} else {
			return datosperfilDao.findByIdInCentroDatoWithKeywordDistint(pageable, centros, keyword, datos, desde, hasta);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Object> findByIdInRegionCentroWithKeywordDistint(Pageable pageable, List<Long> regiones, String keyword, List<Long> centros, Date desde, Date hasta) {
		// TODO Auto-generated method stub
		if (regiones == null) {
			return findByIdInCentroWithKeywordDistint(pageable, keyword, centros, desde, hasta);
		} else if (centros == null) {
			return findByIdInRegionWithKeywordDistint(pageable, regiones, keyword, desde, hasta);
		} else {
			return datosperfilDao.findByIdInRegionCentroWithKeywordDistint(pageable, regiones, keyword, centros, desde, hasta);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Object> findByIdInWithKeywordDistint(Pageable pageable, List<Long> regiones, String keyword,
			List<Long> centros, List<String> datos, Date desde, Date hasta) {
		// TODO Auto-generated method stub
		if (datos == null) {
			return findByIdInRegionCentroWithKeywordDistint(pageable, regiones, keyword, centros, desde, hasta);
		} else {
			if (regiones == null) {
				return findByIdInCentroDatoWithKeywordDistint(pageable, centros, keyword, datos, desde, hasta);
			} else if (centros == null) {
				return findByIdInRegionDatoWithKeywordDistint(pageable, regiones, keyword, datos, desde, hasta);
			} else {
				return datosperfilDao.findByIdInWithKeywordDistint(pageable, regiones, keyword, centros, datos, desde, hasta);
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long sumaPositivosPerfil(Long datofechaId) {
		// TODO Auto-generated method stub
		return datosperfilDao.sumaPositivosPerfil(datofechaId);
	}

	@Override
	@Transactional(readOnly = true)
	public Long cuentaDatosCentro(Long centroId) {
		// TODO Auto-generated method stub
		return datosperfilDao.cuentaDatosCentro(centroId);
	}

	@Override
	@Transactional(readOnly = true)
	public Long cuentaDatosPregunta(Long preguntaId) {
		// TODO Auto-generated method stub
		return datosperfilDao.cuentaDatosPregunta(preguntaId);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Object> findAllWithKeywordDistintObject(Pageable pageable, String keyword, Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return datosperfilDao.findAllWithKeywordDistintObject(pageable, keyword, desde, hasta);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Long> cuentaPrueba(Long preguntaId) {
		// TODO Auto-generated method stub
		return datosperfilDao.cuentaPrueba(preguntaId);
	}


}
