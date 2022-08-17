package org.study.warriors.model.request.type;

import org.study.warriors.model.damage.IDamage;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.request.Request;

public class DamageRequest extends Request {

    private final IDamage damageType;

    public DamageRequest(IWarrior requestSender, IDamage damageType) {
        super(requestSender);
        this.damageType = damageType;
    }

    public IDamage getDamageType() {
        return damageType;
    }
}
