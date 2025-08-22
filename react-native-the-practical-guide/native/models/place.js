class Place {
  constructor(title, imageUrl, address, location) {
    this.id = new Date().toString() + Math.random().toString();
    this.title = title;
    this.imageUrl = imageUrl;
    this.address = address;
    this.location = location; // {lat: 0.141241, lng: 127.121}
  }
}
