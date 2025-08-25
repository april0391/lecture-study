export class Place {
  constructor(title, imageUri, location) {
    this.id = new Date().toString() + Math.random().toString();
    this.title = title || "default";
    this.imageUri = imageUri;
    this.address = location?.address || "default address";
    this.location = { lat: location?.lat, lng: location?.lng };
  }
}
