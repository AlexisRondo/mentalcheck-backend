package com.fiap.mentalcheck.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ProcedureService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Long inserirUsuarioProcedure(String nome, String email, String senha,
                                        String cargo, String modalidadeTrabalho) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_INSERIR_USUARIO");

        query.registerStoredProcedureParameter("p_nome", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_senha", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_cargo", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_modalidade_trabalho", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_usuario", BigDecimal.class, ParameterMode.OUT);

        query.setParameter("p_nome", nome);
        query.setParameter("p_email", email);
        query.setParameter("p_senha", passwordEncoder.encode(senha));
        query.setParameter("p_cargo", cargo);
        query.setParameter("p_modalidade_trabalho", modalidadeTrabalho);

        query.execute();

        BigDecimal idUsuario = (BigDecimal) query.getOutputParameterValue("p_id_usuario");
        return idUsuario != null ? idUsuario.longValue() : null;
    }

    @Transactional
    public Long inserirCheckinProcedure(Long idUsuario, Integer nivelEstresse,
                                        Integer nivelMotivacao, Integer nivelCansaco,
                                        Integer nivelSatisfacao, Integer qualidadeSono,
                                        String localTrabalho, String observacao) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_INSERIR_CHECKIN");

        query.registerStoredProcedureParameter("p_id_usuario", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nivel_estresse", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nivel_motivacao", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nivel_cansaco", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nivel_satisfacao", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_qualidade_sono", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_local_trabalho", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_observacao", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_checkin", BigDecimal.class, ParameterMode.OUT);

        query.setParameter("p_id_usuario", BigDecimal.valueOf(idUsuario));
        query.setParameter("p_nivel_estresse", BigDecimal.valueOf(nivelEstresse));
        query.setParameter("p_nivel_motivacao", BigDecimal.valueOf(nivelMotivacao));
        query.setParameter("p_nivel_cansaco", BigDecimal.valueOf(nivelCansaco));
        query.setParameter("p_nivel_satisfacao", BigDecimal.valueOf(nivelSatisfacao));
        query.setParameter("p_qualidade_sono", BigDecimal.valueOf(qualidadeSono));
        query.setParameter("p_local_trabalho", localTrabalho);
        query.setParameter("p_observacao", observacao);

        query.execute();

        BigDecimal idCheckin = (BigDecimal) query.getOutputParameterValue("p_id_checkin");
        return idCheckin != null ? idCheckin.longValue() : null;
    }

    @Transactional
    public Long inserirDicaProcedure(String titulo, String descricao, String categoria,
                                     String condicaoAplicacao) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_INSERIR_DICA");

        query.registerStoredProcedureParameter("p_titulo", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_descricao", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_categoria", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_condicao_aplicacao", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_dica", BigDecimal.class, ParameterMode.OUT);

        query.setParameter("p_titulo", titulo);
        query.setParameter("p_descricao", descricao);
        query.setParameter("p_categoria", categoria);
        query.setParameter("p_condicao_aplicacao", condicaoAplicacao);

        query.execute();

        BigDecimal idDica = (BigDecimal) query.getOutputParameterValue("p_id_dica");
        return idDica != null ? idDica.longValue() : null;
    }

    public Double calcularMediaEstresse(Long idUsuario, Integer dias) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("FN_CALCULAR_MEDIA_ESTRESSE");

        query.registerStoredProcedureParameter("p_id_usuario", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_dias", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("return", BigDecimal.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_id_usuario", BigDecimal.valueOf(idUsuario));
        query.setParameter("p_dias", BigDecimal.valueOf(dias));

        query.execute();

        BigDecimal media = (BigDecimal) query.getSingleResult();
        return media != null ? media.doubleValue() : 0.0;
    }
}