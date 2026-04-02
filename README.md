[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/e4rOHRfR)

# UVA Campus Map

**Name:** Marvin Rivera
**Computing ID:** tkk9wg

A single-screen Android app that displays University of Virginia locations on a Google Map, filtered by tag.

## How It Works

On launch, the app fetches location data from the UVA placemarks API and stores it in a local SQLite database using Room. After the first sync, all data is read from the database. Duplicate entries are prevented by using a replace-on-conflict strategy keyed on each location's ID.

## Setup

To build and run this app you need a Google Maps API key.

1. Get a key from the Google Cloud Console with the Maps SDK for Android enabled.
2. Add it to `local.properties` in the project root (this file is gitignored):

```
MAPS_API_KEY=your_key_here
```

3. Build and run.
