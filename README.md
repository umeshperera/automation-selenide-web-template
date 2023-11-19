<a name="readme-top"></a>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/umeshperera/automation-selenide-web-template">
    <img src="logo.png" alt="Logo" width="500" height="80">
  </a>

<h3 align="center">Automation Framework - Template</h3>

  <p align="center">
    🚀 Empower Your Team with No-Code Testing! 🌐✨ A simplified automation framework built upon Selenide, Cucumber, and JUnit, designed for non-tech-savvy heroes. Write tests in plain English, create scenarios effortlessly, and watch your web application come to life! 🌈 Easy, Fun, Powerful – Testing for Everyone! 🚀 #NoCodeTesting #Selenide #Cucumber #JUnit
    <br />
    <a href="https://github.com/umeshperera/automation-selenide-web-template/wiki"><strong>Explore the wiki »</strong></a>
    <br />
    <br />
    <a href="https://github.com/umeshperera/automation-selenide-web-template">View Sample Report</a>
    ·
    <a href="https://github.com/umeshperera/automation-selenide-web-template/issues">Report Bug</a>
    ·
    <a href="https://github.com/umeshperera/automation-selenide-web-template/issues">Request Features</a>
  </p>
</div>

<!-- ABOUT THE PROJECT -->
## About The Project
This project, built upon Selenide, Cucumber, and JUnit, is not just an automation framework but a journey into effortless testing for non-tech-savvy enthusiasts.

## Features:

🌐 Automatic Webdriver Handling: Say goodbye to browser compatibility concerns! Support for "chrome," "firefox," "ie," "edge," and "safari" out of the box.

🌈 Headless Execution: Run tests in the background with ease, keeping your workflows smooth and interruption-free.

🖥️ Custom Browser Sizes: Tailor your web browser dimensions to fit your testing needs. It's all about flexibility!

📊 Isolated Test Data and Locator Handling: Keep your data and locators organized and isolated for seamless maintenance and readability.

📘 User-Readable Scripts: Tests are written in plain English, making them accessible and understandable for everyone on the team.

📈 CLI Report: Stay in the loop with concise command line reports, providing instant insights into your test runs.

🎨 Beautiful Cucumber Report: Visualize your test results in an elegant and informative format, making analysis a breeze.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

* [![Selenide][Selenide]][Selenide-url]
* [![Cucumber][Cucumber]][Cucumber-url]
* [![Selenium][Selenium]][Selenium-url]
* [![Junit][Junit]][Junit-url]
* [![Maven][Maven]][Maven-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
### Getting Started
To get a local copy up and running follow these simple steps.

### Prerequisites
| Requirement | Description |
| ------ | ------ |
| Java SE Development Kit 19 or latest | [Download][ReQJava] |
| IntelliJ IDEA (recommended) or eclipse | IntelliJ IDEA community edition (free version) is more than enough [Download][ReqIDE] |
| Browser | • Chrome <br> • Firefox <br> • Internet Explorer <br> • Edge <br> • Safari (macOS Only) |

### Open Project
1. **Clone the repo**
```sh
  git clone git@github.com:umeshperera/automation-selenide-web-template.git
```
2. **Create Your Own Repo:**<br>
    Alternatively, jumpstart your project using this template. Refer GitHub Documentation  ([Refer][GithubDoc])
3. **Open Your Preferred IDE:**<br>
    Fire up your Jetbrains IDEA or Eclipse, whatever suits your style.
4. **Open the Project:**<br>
    Navigate to the project directory and open it via the file menu.

**Note**: Leave IDE open for a few minute to download project dependencies

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Usage

You can execute your scripted tests using CLI
```Java
  mvn clean test
```
or

You can execute selected cucumber scenarios by refererring it like follows.
```Java
  mvn test -Dcucumber.filter.tags="@Login"
```

_For more examples, please refer to the [Documentation](https://github.com/umeshperera/automation-selenide-web-template/wiki)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>

# Changelog

## [Latest] - 2023-11-19
 
- Initial Release

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- Visual regression testing

See the [open issues](https://github.com/umeshperera/automation-selenide-web-template/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distributed under the MIT License. See [License][License_url] for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Your Name - [@umeshchamara](https://twitter.com/umeshchamara)

Project Link: [https://github.com/umeshperera/automation-selenide-web-template](https://github.com/umeshperera/automation-selenide-web-template)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments
* [Selnide][Selenide-url]
* [Selenium][Selenium-url]
* [Cucumber][Cucumber-url]
* [JUnit][Junit-url]
* [Apache Maven][Maven-url]
* [Fillo][Fillo-url]
* [Datafaker][Datafaker-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/umeshperera/automation-selenide-web-template.svg?style=for-the-badge
[contributors-url]: https://github.com/umeshperera/automation-selenide-web-template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/umeshperera/automation-selenide-web-template.svg?style=for-the-badge
[forks-url]: https://github.com/umeshperera/automation-selenide-web-template/network/members
[stars-shield]: https://img.shields.io/github/stars/umeshperera/automation-selenide-web-template.svg?style=for-the-badge
[stars-url]: https://github.com/umeshperera/automation-selenide-web-template/stargazers
[issues-shield]: https://img.shields.io/github/issues/umeshperera/automation-selenide-web-template.svg?style=for-the-badge
[issues-url]: https://github.com/umeshperera/automation-selenide-web-template/issues
[license-shield]: https://img.shields.io/github/license/umeshperera/automation-selenide-web-template.svg?style=for-the-badge
[license-url]: https://github.com/umeshperera/automation-selenide-web-template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/umesh-chamara

<!--Custom URLs-->
[ReQJava]: <https://www.oracle.com/java/technologies/downloads/>
[ReqIDE]: <https://www.jetbrains.com/idea/download/>
[GithubDoc]: https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template
[License_url]: https://choosealicense.com/licenses/mit/

<!--Logos-->
[Selenide]: https://img.shields.io/badge/Selenide-e000ff?style=for-the-badge&logo=data:image/png;base64,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&logoColor=white
[Selenide-url]: https://selenide.org/
[Selenium]: https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white
[Selenium-url]: https://www.selenium.dev/
[Cucumber]: https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=Cucumber&logoColor=white
[Cucumber-url]: https://cucumber.io/
[Junit]: https://img.shields.io/badge/JUnit5-f6c344?style=for-the-badge&logo=JUnit5&logoColor=white
[Junit-url]: https://junit.org/junit5/
[Maven]: https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white
[Maven-url]: https://maven.apache.org/
[Fillo-url]: https://codoid.com/products/fillo/#post_productdownload!
[Datafaker-url]: https://www.datafaker.net/
