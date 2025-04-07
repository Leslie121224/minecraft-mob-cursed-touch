# 🧪 Minecraft Mob Cursed Touch

A simple Forge mod for Minecraft 1.21.4 where touching any mob (passive or hostile) will apply a random potion effect to the player for 15 seconds.

---

## 💡 Features

- 👣 Touch any living mob → receive a **random potion effect**
- 🧍 Passive or hostile mobs both count (e.g. cow, zombie, villager)
- 🎲 Potion effects include both **positive and negative effects**
- ⏳ 1-second cooldown between each activation

---

## 📦 Installation

1. Install [Minecraft Forge 1.21.4](https://files.minecraftforge.net/)
2. Download the latest `minecraft-mob-cursed-touch-*.jar` from [Releases](#)
3. Put the `.jar` into your `mods/` folder
4. Launch the game and enjoy the chaos!

---

## 🛠 Development

Mod ID: `cursedtouch`  
Main Class: `com.leslie121224.cursedtouch.CursedTouchMod`

Event handler:
- `PlayerTouchEntityHandler.java` (checks bounding box overlap and applies effect)

---

## 📜 License

MIT License © 2025 [leslie121224](https://github.com/leslie121224)
