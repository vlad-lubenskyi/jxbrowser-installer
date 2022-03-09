## jxbrowser-installer

A simple project that demonstrates how to create installers for JxBrowser applications using `jpackage`.

### System Requirements

To use this project follow the steps:

1. Install JDK 16.
2. For Windows, install [WiX Toolset](https://wixtoolset.org/releases/).
3. Make sure your environment meets
   the [requirements](https://jxbrowser-support.teamdev.com/docs/guides/introduction/requirements.html) of JxBrowser.
4. [Request](https://www.teamdev.com/jxbrowser#evaluate) a free 30-day trial license key.

### Usage Example

1. In the project directory, execute the command to generate an installer:
   ```bash
   ./gradlew jpackage
   ```

2. Find the installer in `build/dist` directory.

---

The information in this repository is provided on the following terms: https://www.teamdev.com/terms-and-privacy
