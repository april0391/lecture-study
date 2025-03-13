const form = document.querySelector("form")!;
const addressInput = document.getElementById("address")! as HTMLInputElement;

declare var ol: any;

function searchAddressHandler(event: Event) {
  event.preventDefault();

  const coordinates = { lat: 40.41, lng: -73.99 }; // Google API에서 좌표를 가져올 수 없음, 더미 API 사용

  document.getElementById("map")!.innerHTML = ""; // <div id="map">에서 <p> 제거
  new ol.Map({
    target: "map",
    layers: [
      new ol.layer.Tile({
        source: new ol.source.OSM(),
      }),
    ],
    view: new ol.View({
      center: ol.proj.fromLonLat([coordinates.lng, coordinates.lat]),
      zoom: 16,
    }),
  });
}

form.addEventListener("submit", searchAddressHandler);
