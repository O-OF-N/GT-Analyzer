package org.gtd.transform.fNs;

import java.util.*;

import org.apache.crunch.MapFn;
import org.apache.crunch.Pair;
import org.gtd.*;

public class ResultFn extends MapFn<Pair<String, Iterable<Pair<String, AttackDetails>>>, AttacksPerCountryPerYear> {

    private final Map<String, List<AttackDetails>> attackDetailsMap = new LinkedHashMap<String, List<AttackDetails>>();
    private final List<AttacksPerCountry> attacksPerCountry = new ArrayList<AttacksPerCountry>();

    public AttacksPerCountryPerYear map(Pair<String, Iterable<Pair<String, AttackDetails>>> y) {
        AttacksPerCountryPerYear.Builder attacksPerCountryPerYear = AttacksPerCountryPerYear
                .newBuilder().setYear(y.first().toString());

        Iterator<Pair<String, AttackDetails>> attackDetailsIterator = y.second().iterator();

        while (attackDetailsIterator.hasNext()) {
            AttackDetails attackDetailsWritable = attackDetailsIterator
                    .next().second();
            Location location = Location.newBuilder(attackDetailsWritable.getLocation())
                    .build();
            Target target = Target.newBuilder(attackDetailsWritable.getTarget()).build();
            Time time = Time.newBuilder(attackDetailsWritable.getTime()).build();
            Attack attack = Attack.newBuilder(attackDetailsWritable.getAttack()).build();
            AttackDetails attackDetails = AttackDetails.newBuilder().setTime(time).setTarget(target).setLocation(location)
                    .setAttack(attack).build();
            String country = attackDetails.getLocation().getCountry().toString();

            if (!attackDetailsMap.containsKey(country))
                attackDetailsMap.put(country, new ArrayList<>());
            attackDetailsMap.get(country).add(attackDetails);
        }
        for (Map.Entry<String, List<AttackDetails>> entry : attackDetailsMap.entrySet()) {
            AttacksPerCountry a = AttacksPerCountry.newBuilder().setCountry(entry.getKey())
                    .setCount(entry.getValue().size()).build();
            attacksPerCountry.add(a);
        }
        attacksPerCountryPerYear.setAttackByCountry(attacksPerCountry);
        return attacksPerCountryPerYear.build();
    }
}
