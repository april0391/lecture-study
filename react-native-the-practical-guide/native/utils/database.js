import * as SQLite from "expo-sqlite";

let db;

export async function init() {
  if (db) return;

  try {
    db = await SQLite.openDatabaseAsync("places.db");
    await db.execAsync(`
      PRAGMA journal_mode = WAL;
      CREATE TABLE IF NOT EXISTS places (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        title TEXT NOT NULL,
        imageUri TEXT NOT NULL,
        address TEXT NOT NULL,
        lat REAL NOT NULL,
        lng REAL NOT NULL
      );
    `);
    console.log("places table created.");
  } catch (error) {
    console.error("Failed to create places table.", error);
    throw error;
  }
}

export async function insertPlace(place) {
  const result = await db.runAsync(
    "INSERT INTO places (title, imageUri, address, lat, lng) VALUES (?, ?, ?, ?, ?)",
    [
      place.title,
      place.imageUri,
      place.address,
      place.location.lat,
      place.location.lng,
    ]
  );
  console.log("result", result);

  return result;
}

export async function fetchPlaces() {
  const allRows = await db.getAllAsync("SELECT * FROM places");
  return allRows;
}
