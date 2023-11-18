# Spring Boot important annotations


## Spring Data JPA Üçün İşarələr (Annotations)

1. @Entity: Sinfi JPA entity (persistent domain obyekti) kimi işarələyir. Adətən, entity'nin əsas cədvəli üçün @Table ilə istifadə olunur.
2. @Table: @Entity ilə birlikdə istifadə olunaraq, entity'nin verilənlər bazasında hansı cədvəllə uyğunlaşdırıldığını göstərir.
3. @Id: Bir sahəni entity'nin əsas açarı kimi işarələyir.
4. @GeneratedValue: Əsas açarın necə yaradılacağını təyin edir. Məsələn, avtomatik artırıla bilər.
5. @Column: Davamlı xüsusiyyət və ya sahə üçün uyğunlaşdırılmış sütunu təyin edir. Əgər heç bir
@Column təyin edilməyibsə, varsayılan sütun adı sahə adı ilə eynidir.
6. @ManyToOne, @OneToMany, @OneToOne, @ManyToMany: Müxtəlif növ entity əlaqələrini təyin edir.
7. @JoinColumn: Bir entity əlaqəsi və ya element kolleksiyasının qoşulma sütununu təyin edir.
8. @Repository: Sinfin bir repository olduğunu göstərir, @Component-in ixtisaslaşmasıdır.
9. @Transactional: Bir metod və ya sinifdə əməliyyat xüsusiyyətlərini təsvir edir.
10. @Query: Repository metodları üzərində bir JPA sorğusunu birbaşa təyin etmək üçün istifadə olunur.

## Spring Web Üçün İşarələr (Annotations)
1. @RestController: @Controller və @ResponseBody birləşdirən rahatlıq işarəsi, hər metod tərəfindən qaytarılan məlumatların birbaşa cavab bədəninə yazılacağını göstərir.
2. @RequestMapping: Web sorğularını müəyyən idarəçi sinifləri və/ya idarəçi metodlarına uyğunlaşdırmaq üçün işarə. İndi tez-tez @GetMapping, @PostMapping və s. kimi daha spesifik işarələrlə əvəz olunur.
3. @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping: @RequestMapping-in xüsusiyyətləri, metodun cavab verməli olduğu HTTP metodunu göstərmək üçün qısayollar.
4. @PathVariable: Bir metod parametrinin URI şablon dəyişəni ilə bağlanmalı olduğunu göstərir.
5. @RequestParam: Bir metod parametrinin veb sorğu parametri ilə bağlanmalı olduğunu göstərir.
6. @RequestBody: Bir metod parametrinin veb sorğu bədəni ilə bağlanmalı olduğunu göstərir.
7. @ResponseBody: Kontrollerə qaytarılan obyektin avtomatik olaraq JSON-a seriallaşdırılacağını və HttpResponse obyektinə geri yazılacağını bildirir.
8. @ControllerAdvice: Bütün tətbiq boyu istisnaları idarə etməyə imkan verir, yalnız fərdi bir kontrollerə deyil. Həmçinin bütün @RequestMapping metodlarına tətbiq olunan @ModelAttribute, @InitBinder, və


## Spring Data JPA İşarələri nnümunələr ilə

### 1. @Entity

- İzah: Sinfi JPA entity (persistent domain obyekti) kimi işarələyir. Bu, sinfin verilənlər bazasında bir cədvələ uyğunlaşdırıldığını bildirir.

```java
@Entity
public class Istifadeci {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // digər xüsusiyyətlər...
}
```

### 2. @Table

- İzah: Entity'nin verilənlər bazasındakı uyğun cədvəlini təyin edir.

```java
@Entity
@Table(name = "istifadeciler")
public class Istifadeci {
    // xüsusiyyətlər...
}
```

### 3. @Id

- İzah: Bir sahəni entity'nin əsas açarı kimi işarələyir.

```java
@Entity
public class Istifadeci {
    @Id
    private Long id;
    // digər xüsusiyyətlər...
}
```

### 4. @ManyToOne, @OneToMany

- İzah: Müxtəlif növ entity əlaqələrini (münasibətlərini) təyin edir. Məsələn, @ManyToOne bir çoxdan birmənaya (many-to-one) münasibəti, @OneToMany isə birdən çoxa (one-to-many) münasibəti ifadə edir.

```java
@Entity
public class Kitab {
    @ManyToOne
    private Istifadeci sahib;
    // digər xüsusiyyətlər...
}
```
## Spring Web İşarələri

### 1. @RestController

- İzah: Web servisləri üçün kontroller sinifini işarələyir. Bu, sinfin bütün cavablarının REST API vasitəsilə JSON formatında olduğunu bildirir.

```java
@RestController
public class IstifadeciController {
    // HTTP sorğularını emal edən metodlar...
}
```

### 2. @GetMapping, @PostMapping

- İzah: HTTP GET və POST metodlarına cavab verən kontroller metodlarını işarələyir.

```java
@RestController
public class IstifadeciController {
    @GetMapping("/istifadeciler")
    public List<Istifadeci> butunIstifadecileriAl() {
        // kod...
    }

    @PostMapping("/istifadeciler")
    public Istifadeci yeniIstifadeciElaveEt(@RequestBody Istifadeci istifadeci) {
        // kod...
    }
}
```
### 3. @PathVariable

- İzah: URI'dəki bir dəyişənin metod parametrinə bağlanmasını təmin edir.

```java
@GetMapping("/istifadeciler/{id}")
public Istifadeci istifadeciAl(@PathVariable Long id) {
    // kod...
}
```
### 4. @RequestBody

- İzah: HTTP sorğu bədənindəki məlumatın metod parametrinə bağlanmasını təmin edir, əsasən JSON formatında.

```java
@PostMapping("/istifadeciler")
public Istifadeci istifadeciEl
```