<h1>Pemrograman Lanjut</h1>

<h2>
   
   Nama  : Muhammad Akmal Abdul Halim
   
   NPM   : 2306245125
  
   Kelas : B
  
</h2>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<details>
  <summary> Tutorial 1 </summary>

**Reflection 1**

- Dari tutorial yang sudah saya kerjakan, hal yang saya pelajari adalah nama variable harus memiliki arti yang sesuai. Ini berguna agar
kode kita dapat lebih mudah dibaca. Hal lain yang saya pelajari adalah kita harus selalu melakukan validasi input dari user. Ini berguna untuk
memperkuat dan mengamankan program yang kita buat. Setelah saya mengerjakan tutorial 1 ini, saya melakukan beberapa kesalahan. Kesalahan yang saya lakukan
adalah ketidaktelitian saya dalam melakukan routing antara front end dan back end sehingga terjadi error. Yang bisa diperbaiki dari ini adalah saya harus lebih teliti
lagi dalam melakukan routing baik di front end(Html) maupun back end(Java). Selanjutnya mungkin dalam kode saya masih belum cukup dalam memvalidasi
input user dan autentikasi. Ini akan berakibat kepada masalah keamanan. Hal yang bisa saya lakukan ialah menambahkan validasi dan autentikasi.

**Reflection 2**
1. Setelah saya melakukan unit test, saya merasa bahwa unit test merupakan hal yang penting dalam pengembangan sebuah program.
Unit test berguna untuk memastikan bahwa program berjalan lancar dan tidak ada bug. Menurut saya unit test harus dilakukan untuk setiap
method/fitur yang kita implementasi dalam program kita. Untuk memastikan apakah unit test kita cukup atau tidak adalah dengan memastikan tiap method
memiliki testnya sendiri. Menurut saya, jika kita memiliki 100% code coverage tidak berarti kode kita bebas dari error dan bug. Code coverage hanya mengukur sejauh mana kode dieksekusi, namun tidak menilai kualitas atau cakupan skenario pengujian.


2. Ketika membuat kelas fungsional baru yang memiliki prosedur setup dan variabel instan yang sama dengan kelas sebelumnya, ada potensi duplikasi kode. Meskipun secara fungsional test tersebut bisa berjalan dengan baik, dari sisi clean code terdapat beberapa masalah:

    - Duplikasi Kode (DRY Violation):
    
    Mengulang kode setup yang sama di beberapa kelas test melanggar prinsip Don't Repeat Yourself (DRY). Jika ada perubahan pada setup (misalnya format URL dasar atau konfigurasi driver), saya harus mengubahnya di setiap kelas, yang berpotensi menyebabkan inkonsistensi.
    
    - Pemeliharaan yang Sulit:
    
    Kode yang diduplikasi membuat pemeliharaan menjadi lebih sulit. Setiap kali ada update atau perbaikan pada logika setup, saya harus memastikan semua kelas test diperbarui secara serempak.
    
    - Reusabilitas yang Rendah:
    
    Duplikasi setup di berbagai kelas menunjukkan bahwa ada bagian kode yang bisa di-refactor menjadi komponen reusable, sehingga perubahan di masa depan dapat dilakukan di satu tempat saja.


</details>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<details>
   <summary> Tutorial 2 </summary>   

**Reflection**

1. List Code Quality Issue yang saya perbaiki :
   
   - Pinned-Dependencies
   - Unnecessary modifier 'public'

   Strategi saya dalam memperbaiki code quality issue ini adalah dengan melihat detail log yang diberikan
   oleh workflow yang saya implementasikan. Lalu dari log tersebut saya lakukan perbaikan sesuai informasi yang diberikan.


2. Menurut saya sudah, Implementasi repository saya sudah mendekati prinsip Continuous Integration (CI) dan Continuous 
Deployment (CD). Proses CI terlihat dari adanya unit test yang berjalan secara otomatis dengan konfigurasi Gradle dan 
integrasi dengan PMD melalui GitHub Actions, sehingga setiap push dan pull request diuji untuk menjaga kualitas kode. 
Sedangkan untuk CD, adanya mekanisme auto-deployment ke PaaS Koyeb (dengan instruksi push-based deployment) memungkinkan 
aplikasi untuk secara otomatis di-deploy ke lingkungan produksi saat ada perubahan, sehingga memenuhi prinsip deployment 
berkelanjutan. Secara keseluruhan, repository saya telah mengadopsi prinsip CI/CD dengan baik, meskipun selalu ada ruang 
untuk perbaikan dan penambahan fitur seperti integrasi testing lebih komprehensif atau rollback otomatis jika terjadi kegagalan 
deployment.

</details>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<details>
   <summary> Tutorial 3 </summary>
Reflection

1. Explain what principles you apply to your project!
   - Single Responsibility Principle (SRP):
   
   Setiap kelas atau modul memiliki satu tanggung jawab utama. Misalnya, kelas CarRepository hanya bertanggung jawab untuk operasi CRUD (create, read, update, delete) pada entitas Car dan tidak mencampur logika lain. Hal yang sama diterapkan pada ProductRepository, sehingga setiap komponen hanya menangani satu aspek dari aplikasi.

   - Open/Closed Principle (OCP):
   
   Kelas atau modul harus terbuka untuk ekstensi namun tertutup untuk modifikasi. Di proyek ini, misalnya, kami telah membuat interface dasar (seperti ICarRepository dan IProductRepository) dan mengembangkan kelas ekstensi (seperti CarRepositoryExtendedImpl dan ProductRepositoryExtendedImpl) untuk menambahkan fitur baru (misalnya, pencarian berdasarkan atribut) tanpa mengubah kode dasar yang sudah teruji.
   
   - Dependency Inversion Principle (DIP):
   
   Modul tingkat tinggi (seperti service dan controller) tidak bergantung pada modul tingkat rendah (seperti repository konkrit), melainkan pada abstraksi (interface). Contohnya, kelas CarServiceImpl dan ProductServiceImpl hanya bergantung pada interface (ICarRepository dan IProductRepository) sehingga memungkinkan fleksibilitas untuk mengganti implementasi repository jika diperlukan.


2.  Explain the advantages of applying SOLID principles to your project with examples.
   - Maintainability (Kemudahan Pemeliharaan):
     
Dengan SRP, setiap kelas memiliki satu tanggung jawab. Contohnya, jika ada perubahan pada cara penyimpanan data Car, hanya CarRepository yang perlu diperbaiki, tanpa mempengaruhi kelas lain. Ini membuat perbaikan bug dan pemeliharaan menjadi lebih mudah dan terisolasi.

   - Extensibility (Kemudahan Ekstensi Fitur):
   
Penerapan OCP memungkinkan Anda menambahkan fungsionalitas baru tanpa mengubah kode yang sudah ada. Misalnya, jika di kemudian hari Anda ingin menambahkan fitur pencarian mobil berdasarkan warna, Anda cukup membuat interface ekstensi ExtendedCarRepository dan mengimplementasikannya di kelas baru (CarRepositoryExtendedImpl). Kode dasar CarRepository tidak diubah, sehingga risiko terjadinya bug pada fungsionalitas lama menjadi minim.
   
   - Testability (Kemudahan Pengujian):
   
Dengan DIP, service dan controller tidak bergantung langsung pada implementasi konkrit repository. Karena mereka hanya bergantung pada abstraksi (interface), Anda bisa dengan mudah membuat mock atau stub untuk melakukan unit testing. Misalnya, CarServiceImpl dapat diuji dengan memberikan implementasi palsu dari ICarRepository, sehingga pengujian dapat dilakukan secara terisolasi tanpa perlu bergantung pada data nyata.
   
   - Flexibility (Fleksibilitas Perubahan):
   
Karena komponen-komponen bergantung pada abstraksi, Anda dapat dengan mudah mengganti implementasi detail (misalnya, beralih ke database yang berbeda atau menambah fitur baru) tanpa harus merombak kode di level yang lebih tinggi. Ini membuat sistem lebih adaptif terhadap perubahan kebutuhan bisnis.

3. Explain the disadvantages of not applying SOLID principles to your project with examples.
   - Keterikatan Kode (Tight Coupling):
     
   Tanpa DIP, modul tingkat tinggi akan bergantung langsung pada implementasi konkrit. Misalnya, jika CarService langsung menggunakan CarRepository tanpa abstraksi, maka setiap perubahan pada CarRepository (misalnya, perubahan logika atau metode baru) bisa langsung mempengaruhi CarService, yang menyebabkan sulitnya melakukan perubahan tanpa risiko merusak fungsionalitas lain.

   - Kode yang Sulit Dipelihara (Hard to Maintain):
   
   Tanpa SRP, satu kelas mungkin menangani banyak tanggung jawab. Hal ini menyebabkan kelas menjadi besar, kompleks, dan sulit untuk dimengerti. Contohnya, jika ProductRepository juga menangani validasi bisnis atau logging, setiap perubahan pada satu aspek bisa menyebabkan efek samping yang tidak diinginkan pada aspek lain, sehingga debugging menjadi lebih rumit.
   
   - Sulit untuk Ekstensi (Lack of Extensibility):
   
   Tanpa menerapkan OCP, menambahkan fitur baru seringkali mengharuskan modifikasi pada kode yang sudah ada. Misalnya, jika ingin menambahkan metode pencarian baru pada CarRepository yang belum dirancang untuk ekstensi, Anda harus mengubah kelas yang sudah ada. Hal ini meningkatkan kemungkinan terjadinya bug dan membuat sistem menjadi kurang fleksibel terhadap perubahan kebutuhan.
   
   - Pengujian yang Rumit (Difficult to Test):
   
   Tanpa abstraksi yang disediakan oleh DIP, unit testing menjadi lebih sulit. Komponen yang saling terkait secara langsung (tight coupling) membuat sulit untuk mengisolasi bagian tertentu dari sistem dalam pengujian. Akibatnya, penulisan unit test menjadi lebih kompleks dan kurang efektif dalam menemukan bug.
</details>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
<details>
   <summary> Tutorial 4 </summary>

Reflection
1. Secara umum, TDD membantu saya menulis kode yang lebih terstruktur karena saya memulai dari kebutuhan fungsional (test) sebelum menulis implementasi. Namun, saya menyadari bahwa dalam praktiknya, ada beberapa hal yang bisa ditingkatkan:

   - Mendefinisikan spesifikasi dengan lebih rinci: Kadang test saya terlalu umum, sehingga ketika implementasi berubah, saya harus menulis ulang banyak test. Di kesempatan berikutnya, saya perlu mendeskripsikan perilaku yang diharapkan secara lebih jelas sebelum menulis test.
   - Membuat test yang benar-benar mewakili kebutuhan bisnis: Terkadang saya menulis test yang sifatnya terlalu teknis, bukan berdasarkan skenario penggunaan nyata. Saya akan fokus pada skenario yang benar-benar mencerminkan workflow pengguna.
   - Menjaga siklus TDD tetap pendek: Saya masih sering menulis banyak kode sebelum kembali ke test. Berikutnya, saya berusaha menjaga siklus “red-green-refactor” lebih singkat agar feedback loop menjadi lebih cepat.

2. Prinsip F.I.R.S.T. mencakup:

   - Fast (Cepat): Test harus dijalankan dalam hitungan detik. Pengujian saya umumnya cepat, tetapi terkadang menunggu inisialisasi database atau konteks Spring. Mungkin perlu memisahkan unit test murni dari integrasi test agar lebih cepat.
   - Independent (Mandiri): Setiap test tidak saling bergantung. Sebagian besar test saya sudah independen, namun ada beberapa kasus di mana test berbagi data global. Saya akan memperbaikinya dengan setup terpisah atau mocking.
   - Repeatable (Dapat diulang): Test harus konsisten di berbagai lingkungan. Selama ini relatif berhasil, tetapi perlu memastikan test tidak bergantung pada konfigurasi lokal tertentu.
   - Self-validating (Self-checking): Test langsung memberitahu apakah pass/fail tanpa perlu dicek manual. Ini sudah saya terapkan dengan assertion yang jelas.
   - Timely (Tepat waktu): Test sebaiknya ditulis sebelum atau bersamaan dengan kode (TDD). Saya kadang menulis test setelah implementasi. Saya perlu lebih disiplin mengikuti TDD agar test benar-benar menjadi acuan desain.
</details>
