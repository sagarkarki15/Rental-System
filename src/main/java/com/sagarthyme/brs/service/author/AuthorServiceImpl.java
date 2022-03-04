package com.sagarthyme.brs.service.author;

import com.sagarthyme.brs.dto.AuthorDto;
import com.sagarthyme.brs.model.Author;
import com.sagarthyme.brs.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    public AuthorRepository authorRepository;

    @Autowired
    public JavaMailSender mailSender;


    @Override
    public List<AuthorDto> findAll() {
        List<Author> authorList = authorRepository.findAll();
        return authorList.stream().map(entity -> AuthorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .mobileNumber(entity.getMobileNumber())
                .build()).collect(Collectors.toList());
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Author entity = Author.builder()
                .id(authorDto.getId())
                .name(authorDto.getName())
                .email(authorDto.getEmail())
                .mobileNumber(authorDto.getMobileNumber())
                .build();
        entity = authorRepository.save(entity);
        return AuthorDto.builder().
                id(entity.getId()).build();
    }

    @Override
    public AuthorDto findById(Integer integer) {
        Optional<Author> optionalAuthor = authorRepository.findById(integer);
        if (optionalAuthor.isPresent()){
            Author author = optionalAuthor.get();
            return AuthorDto.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .email(author.getEmail())
                    .mobileNumber(author.getMobileNumber())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
//        authorRepository.deleteBookByAuthorId(integer);
        authorRepository.deleteById(integer);
    }

    @Value("${spring.mail.username}")
    private String fromName;

    @Value("spring.mail.password")
    private String password;

    @Override
    public void sendMail(AuthorDto authorDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromName);
        message.setTo(authorDto.getEmail());
        authorDto.setSubject("Author registration");
        message.setSubject(authorDto.getSubject());
        authorDto.setMessage("You have successfully been registered as our official author.");
        message.setText(authorDto.getMessage());
        mailSender.send(message);
        System.out.println("Successfully sent!!");
    }
}
