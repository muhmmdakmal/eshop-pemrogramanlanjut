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