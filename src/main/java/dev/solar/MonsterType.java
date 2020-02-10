package dev.solar;

import java.util.Arrays;

public enum MonsterType {
    RUN("달리기", 4),
    FLY("비행", 6),
    ESPURR("에스퍼", 9);

    private final String monsterTypeName;
    private final int minCriteria;

    MonsterType(String monsterTypeName, int minCriteria) {
        this.monsterTypeName = monsterTypeName;
        this.minCriteria = minCriteria;
    }

    public String getMonsterTypeName() { return monsterTypeName; }
    public int getMinCriteria() { return minCriteria; }

    public static MonsterType valueOfType(String inputMonsterType) {
        return Arrays.stream(MonsterType.values())
                .filter(monsterType -> inputMonsterType.equals(monsterType.monsterTypeName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 타입에 맞지않습니다.",inputMonsterType)));
    }
}
