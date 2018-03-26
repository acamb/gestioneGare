# gestioneGare


## Description and warnings
I wrote this software for my local archery club to manage our members-only competitions and tournaments.

The rules used to generate the rankings of the various tournament types are non-standard, a bit messy, undocumented and sometimes just fancy.
The UI (and some of the code) is localized in italian.

As i wrote this in my spare time and only for my archery coach, currently there is no plan to refactor and translate code and  ui in english (or any other languages),
maybe some day i will extract some components as a reusable module in a separate project.

## Project structure
This is a spring boot application + angular 2 that uses myBatis over an embedded H2 database.

### Profiles and builds
Install frontend dependencies running `npm install` in the frontend/ directory.

You can build an executable jar with the custom gradle task 'dist', from the backend/ directory.

The build support 2 profiles: 'test' and 'prod',use the env variable to specify the profile: 

'test' will configure H2 as an in-memory database, 'prod' will persist the database in a file.

For the 'prod' profile copy the database files (.mv.db and .trace.db) in the same folder of the jar.

Example: `gradle -Penv=test dist`







 