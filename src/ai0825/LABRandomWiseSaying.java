package ai0825;

import java.util.Random;

public class LABRandomWiseSaying {
    public static void main(String[] args) {
        String[] wiseSay = {
                "성공은 최종적인 것이 아니며 실패도 치명적인 것이 아니다. 중요한 것은 계속 나아갈 용기다.",
                "기회는 우연히 찾아오는 것이 아니라 준비하고 행동하는 사람이 만들어 가는 것이다.",
                "지금 당장 완벽하지 않아도 괜찮다 중요한 것은 멈추지 않고 조금씩 앞으로 나아가는 것이다.",
                "실패했다는 것은 끝났다는 뜻이 아니라 아직 성공하는 방법을 찾지 못했다는 뜻이다.",
                "작은 노력이라도 매일 반복하면 언젠가는 생각보다 훨씬 큰 차이를 만들어 낸다.",
                "남들과 비교하며 조급해하지 말고 어제의 나보다 조금 더 나아지는 것에 집중하라.",
                "두려움 때문에 시작하지 않는 것보다 서툴더라도 직접 부딪혀 보는 것이 훨씬 낫다.",
                "원하는 삶은 기다린다고 찾아오는 것이 아니라 자신의 선택과 행동으로 만들어 가는 것이다.",
                "힘든 순간이 찾아와도 포기하지 마라 가장 어려운 순간을 지나야 새로운 길이 보이기도 한다.",
                "오늘의 작은 선택과 행동들이 쌓여 결국 미래의 나를 만들어 낸다는 사실을 기억하라."
        };

        Random random = new Random();
        int randomIndex = random.nextInt(wiseSay.length);
        System.out.println("오늘의 명언:" + wiseSay[randomIndex]);
    }
}
