<template>
  <div id="secondpage">
    <!-- Container (About Section) -->
    <div class="header">
      <div class="w3-content w3-container w3-padding-64" id="about">
        <h3 v-scrollanimation class="w3-center">ABOUT Helpromise..</h3>
        <div v-scrollanimation class="w3-center">
          <em>I love photography</em>
        </div>

        <p v-scrollanimation>
          스케줄이 겹쳐서 혼란스러우신 적 있으시다구요?
          <br />일정이 겹치게 되면, 해당 일정에 대한 집중도를 떨어뜨릴 수 있습니다!
          <br />바쁘게 살아가는 일상 속에서, 현대인들은 많은 일정을 수립하고 수행합니다.
          <br />일상 생활, 비즈니스 라이프에서 주변 지인, 그룹 일정 수립시 스케줄 충돌이 발생하는 경우가 있습니다.
          <br />또 스케줄이 많아지게 된다면, 잊어버리는 경우도 생기겠죠?
        </p>

        <div v-scrollanimation>"헬프로미스"로 손쉽고 간편하게 일정을 관리해보세요!</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
#secondpage {
  position: relative;
  min-height: 50vh;
  width: 100vw;
  .header {
    position: relative;
    width: 100vw;
    min-height: 50vh;
    // background-image: url("https://cdn.pixabay.com/photo/2017/02/04/15/25/desk-2037545_1280.png");
    // background-color: #efefef;
    background-position: center;
    background-size: cover;
    overflow: hidden;

    h3 {
      font-family: "Lato", sans-serif;
      transition-delay: 0.4s;
    }

    div {
      //   font-size: 1.2rem;
      transition-delay: 0.1s;
    }
    p {
      padding: 10px 0;
      font-size: 1rem;
      transition-delay: 0.2s;
    }
  }
  .before-enter {
    opacity: 0;
    transform: translateX(100px);
    transition: all 1s cubic-bezier(0.41, 0.01, 0.57, 1.61);
  }

  .enter {
    opacity: 1;
    transform: translateX(0px);
  }
}
</style>