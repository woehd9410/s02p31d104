<template>
  <div class="thirdpage">
    <div v-for="(item,i) in items" v-scrollanimation :key="i">
      <!-- First Grid -->
      <div v-if="(i+1)%2===1">
        <div class="w3-row-padding w3-light-grey w3-padding-64 w3-container">
          <div class="w3-content">
            <div class="w3-third w3-center">
              <i class="fa fa-coffee w3-padding-64 w3-text-red w3-margin-right"></i>
            </div>

            <div class="w3-twothird w3-padding-32">
              <h5>
                <span class="black text-white" style="font-size:1.63rem; padding:5px;">point {{i+1}}</span>
              </h5>
              <h2 class="w3-padding-32">{{item.title}}</h2>

              <div v-for="idx in item.content" :key="idx">
                <p class="w3-text-grey">{{idx}}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Second Grid -->
      <div v-else>
        <div class="w3-row-padding w3-padding-64 w3-container">
          <div class="w3-content">
            <div class="w3-twothird w3-padding-32">
              <h5>
                <span class="black text-white" style="font-size:1.63rem; padding:5px;">point {{i+1}}</span>
              </h5>
              <h2 class="w3-padding-32">{{item.title}}</h2>

              <div v-for="idx in item.content" :key="idx">
                <p class="w3-text-grey">{{idx}}</p>
              </div>
            </div>

            <div class="w3-third w3-center">
              <i class="fa fa-anchor w3-padding-64 w3-text-red"></i>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- <div v-for="(item,i) in items" v-scrollanimation :key="i">
      <div>
        <div style="margin-right:10%">
          <span class="black text-white" style="font-size:1.63rem; padding:10px;">point {{i+1}}</span>
          <div class="ma-5 headline">{{item.title}}</div>
        </div>
      </div>
    </div>-->
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          color: "#ffffff",
          icon: "mdi-star",
          title: "모바일로 언제 어디서나 일정을 간편하게",
          content: [
            "Helproimse의 모든 서비스를 이용할 수 있습니다",
            "모바일에서 멀티 태스킹 가능합니다.",
            "실시간 푸시 알림 기능으로 중요한 일정을 놓칠 틈이 없습니다."
          ],
          open: false,
          styleObject: {
            transform: "translate(0px,0px)"
          },
          description:
            "t is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readab"
        },
        {
          color: "#efefef",
          icon: "mdi-book-variant",
          title: "모바일로 언제 어디서나\n 일정을 간편하게",
          content: [
            "Helproimse의 모든 서비스를 이용할 수 있습니다",
            "모바일에서 멀티 태스킹 가능합니다.",
            "실시간 푸시 알림 기능으로 중요한 일정을 놓칠 틈이 없습니다."
          ],
          open: false,
          styleObject: {
            transform: "translate(0px,0px)"
          },
          description:
            "t is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readab"
        },
        {
          color: "#ffffff",
          icon: "mdi-airballoon",
          title: "모바일로 언제 어디서나\n 일정을 간편하게",
          content: [
            "Helproimse의 모든 서비스를 이용할 수 있습니다",
            "모바일에서 멀티 태스킹 가능합니다.",
            "실시간 푸시 알림 기능으로 중요한 일정을 놓칠 틈이 없습니다."
          ],
          open: false,
          styleObject: {
            transform: "translate(0px,0px)"
          },
          description:
            "t is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readab"
        },
        {
          color: "#efefef",
          icon: "mdi-buffer",
          title: "모바일로 언제 어디서나\n 일정을 간편하게",
          content: [
            "Helproimse의 모든 서비스를 이용할 수 있습니다",
            "모바일에서 멀티 태스킹 가능합니다.",
            "실시간 푸시 알림 기능으로 중요한 일정을 놓칠 틈이 없습니다."
          ],
          open: false,
          styleObject: {
            transform: "translate(0px,0px)"
          },
          description:
            "t is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readab"
        }
      ]
    };
  }
};
</script>

<style lang="scss" scoped>
.thirdpage {
  position: relative;
  min-height: 100vh;
  width: 100vw;
  h1,
  h2,
  h3,
  h4,
  h5,
  h6 {
    font-family: "Lato", sans-serif;
  }
  .fa-anchor,
  .fa-coffee {
    font-size: 200px;
  }
}
.before-enter {
  opacity: 0;
  transform: translateY(100px);
  transition: all 2s ease-out;
}

.enter {
  opacity: 1;
  transform: translateY(0px);
}
</style>