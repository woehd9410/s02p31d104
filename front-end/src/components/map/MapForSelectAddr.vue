<template>
  <div class="map_wrap">
    <div style="height:30px; background-color:white; text-align:center;">
        <strong>Pick address</strong>
    </div>
    <div id="map"></div>
    <div style="height:30px; background-color:white;">
        <v-btn style="float:right;" color="red darken-1" text @click="cancleMapView"
            >Cancle</v-btn
        >
        <v-btn style="float:right;" color="green lighten-2" text @click="updateLatLng">Save</v-btn>
        
    </div>
    
  </div>
</template>

<script>
export default {
    mounted() {
        if (window.kakao && window.kakao.maps) {
            this.initMap();
        } else {
            const script = document.createElement('script');
            /* global kakao */
            script.onload = () => kakao.maps.load(this.initMap);
            script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f5e41cc2b28034cfe4aaf0fabbed798a&libraries=services';
            document.head.appendChild(script);
        }
    },
    methods: {
        updateLatLng(){
          var address = localStorage.getItem("address")
          var latitude = localStorage.getItem("latitude")
          var longitude = localStorage.getItem("longitude")
          localStorage.clear();
          let data = {
              addr : address,
              lat : latitude,
              lng : longitude
          }
          this.$emit('setLatLng', data);
          this.$emit("cancleButtonEvent");
        },
        cancleMapView(){
          this.$emit("cancleButtonEvent");
        },
        initMap() {            
           var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                mapOption = {
                    center: new kakao.maps.LatLng(localStorage.getItem("myLat"),localStorage.getItem("myLng")), // 지도의 중심좌표
                    level: 1 // 지도의 확대 레벨
                };  
            // 지도를 생성합니다    
            localStorage.clear();
            var map = new kakao.maps.Map(mapContainer, mapOption); 

            setTimeout(function(){ map.relayout(); }, 0);
            // 주소-좌표 변환 객체를 생성합니다
            var geocoder = new kakao.maps.services.Geocoder();

            var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
                infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

       
            // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
            kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
                searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
                    if (status === kakao.maps.services.Status.OK) {
                        var detailAddr = !result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                        localStorage.setItem("address" , result[0].address.address_name)
                        localStorage.setItem("latitude" , mouseEvent.latLng.getLat())
                        localStorage.setItem("longitude" , mouseEvent.latLng.getLng())

                        detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
                        
                        var content = '<div class="bAddr">' +
                                        '<span class="title">법정동 주소정보</span>' + 
                                        detailAddr + 
                                    '</div>';

                        // 마커를 클릭한 위치에 표시합니다 
                        marker.setPosition(mouseEvent.latLng);
                        marker.setMap(map);

                        // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
                        infowindow.setContent(content);
                        infowindow.open(map, marker);
                    }   
                });
            });


            function searchDetailAddrFromCoords(coords, callback) {
                // 좌표로 법정동 상세 주소 정보를 요청합니다
                geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
            }

        },
    }
}
</script>

<style>
#map {
    width: 100%;
    height: 300px;
    position: relative;
    overflow: hidden;
}

.map_wrap {position:relative;width:100%;height:360px;}
    .title {font-weight:bold;display:block;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
</style>