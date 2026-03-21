package pratica.picpaySimplificado.dtos;

import pratica.picpaySimplificado.domain.UserType;

import java.math.BigDecimal;

public record RequestUserDTO(String name, String document, String email, String password, BigDecimal saldo, UserType userType) {
}
