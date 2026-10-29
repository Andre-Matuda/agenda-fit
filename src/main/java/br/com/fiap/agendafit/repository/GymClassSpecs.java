package br.com.fiap.agendafit.repository;

import br.com.fiap.agendafit.model.GymClass;
import br.com.fiap.agendafit.model.Level;
import br.com.fiap.agendafit.model.Modality;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;

public class GymClassSpecs {

    public static Specification<GymClass> titleContains(String title) {
        return (root, query, cb) -> title == null ? null :
                cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<GymClass> hasModality(Modality modality) {
        return (root, query, cb) -> modality == null ? null : cb.equal(root.get("modality"), modality);
    }

    public static Specification<GymClass> hasLevel(Level level) {
        return (root, query, cb) -> level == null ? null : cb.equal(root.get("level"), level);
    }

    public static Specification<GymClass> startsAfter(LocalDateTime date) {
        return (root, query, cb) -> date == null ? null : cb.greaterThanOrEqualTo(root.get("startsAt"), date);
    }

    public static Specification<GymClass> startsBefore(LocalDateTime date) {
        return (root, query, cb) -> date == null ? null : cb.lessThanOrEqualTo(root.get("startsAt"), date);
    }

    public static Specification<GymClass> availableOnly(Boolean available) {
        if (available == null || !available) return null;
        return (root, query, cb) -> {
            // Subquery para contar inscrições da aula
            var subquery = query.subquery(Long.class);
            var enrollmentRoot = subquery.from(br.com.fiap.agendafit.model.Enrollment.class);
            subquery.select(cb.count(enrollmentRoot));
            subquery.where(cb.equal(enrollmentRoot.get("gymClass"), root));

            return cb.lessThan(subquery, root.get("capacity"));
        };
    }
}