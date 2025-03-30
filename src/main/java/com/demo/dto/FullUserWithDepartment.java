package com.demo.dto;

import jakarta.persistence.Tuple;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

@EqualsAndHashCode(callSuper = true)
@Data
public class FullUserWithDepartment extends SimpleUserWithDepartment {

        private String email;

        private String phone;

        private Boolean active;

        public FullUserWithDepartment of(Tuple tuple) {
                SimpleUserWithDepartment source = super.of(tuple);
                FullUserWithDepartment target = new FullUserWithDepartment();
                BeanUtils.copyProperties(source, target);
                target.setEmail(tuple.get("email", String.class));
                target.setPhone(tuple.get("phone", String.class));
                target.setActive(tuple.get("active", Boolean.class));
                return target;
        }
}
