package pratica.picpaySimplificado.dtos;

import java.math.BigDecimal;

public record RequestTransaction(BigDecimal value, Long senderId, Long receiverId) {
}
