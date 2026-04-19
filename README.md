Turn-Based Combat Arena (SC2002 OODP Project)
- Project Overview

This project implements a Turn-Based Combat Arena using Java and Object-Oriented Design Principles (OODP), following SOLID principles.

The system simulates a battle between a player-controlled character and multiple enemies, where each combatant takes turns to perform actions such as:

Basic Attack
Defend
Special Skills
Use Items

The game continues until either the player defeats all enemies or is defeated.

Key Features

Turn-Based Combat System
Flexible turn execution based on strategy
Supports multiple combatants (player + enemies)
Dynamic turn order (e.g. speed-based)

Object-Oriented Design
Strong use of:
Abstraction
Encapsulation
Inheritance
Polymorphism

Design Patterns Used
Strategy Pattern → Turn order logic (TurnOrderStrategy)
Factory Pattern → Level and enemy generation (LevelFactory)
Template Method Pattern → Action & StatusEffect hierarchies

Status Effects System
Stun
Invulnerability
Defense Boost
Attack Scaling (Blast Effect)

Item System
Potion (heal)
PowerStone (free skill usage)
SmokeBomb (temporary invulnerability)

Gameplay
Cooldown management
Effect duration handling
Backup enemy spawning

- Project Structure
src/
│
├── main/                # Entry point
├── battle/              # BattleManager (core game logic)
├── strategy/            # Turn order strategies
├── combatant/           # Combatants (Player, Enemy, etc.)
├── action/              # Actions (Attack, Defend, Skills)
├── item/                # Items
└── effect/              # Status effects

How to Run
- Requirements
Java JDK 8 or above
- Steps
Clone the repository:
git clone https://github.com/PalaniSujitha/Turn-Based-Combat-Arena.git
Compile the project:
javac *.java
Run the program:
java Main

Gameplay Instructions
Choose your character (e.g. Warrior / Wizard)
Each turn:
Select an action
Select target(s) if required
Defeat all enemies to win

Design Highlights
- Centralised Game Control
BattleManager handles:
Turn flow
Action execution
Game state updates
- Separation of Concerns
UI is separated from game logic
Improves maintainability and readability
- Extensibility
Easily add:
New characters
New actions
New effects
New enemies

- Team Members
Shi Hongrui
Palani Sujitha
Rui Yifan
Sua Chin Rong
Pradhan Aniket Kumar

- Repository Link

- https://github.com/PalaniSujitha/Turn-Based-Combat-Arena

- Reflection (Summary)
Improved understanding of OODP + SOLID
Learned how to:
Reduce coupling
Design extensible systems
Apply design patterns in real projects

- Notes
This project is developed for SC2002 Object-Oriented Design and Programming
For full design details, refer to the project report

- Bonus
Hidden “cyber gifts” exist in the source code
(You’ll have to explore to find them!)
