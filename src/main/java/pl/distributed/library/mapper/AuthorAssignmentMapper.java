//package pl.distributed.library.mapper;
//
//import pl.distributed.library.dto.AuthorAssignmentDto;
//import pl.distributed.library.dto.BorrowingDto;
//import pl.distributed.library.entity.AuthorAssignment;
//import pl.distributed.library.entity.Borrowing;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//public class AuthorAssignmentMapper {
//    public static Set<AuthorAssignmentDto> authorAssignmentSetToAuthorAssignmentDtoSet(
//            Set<AuthorAssignment> authorAssignments) {
//        return authorAssignments.stream()
//                .map(AuthorAssignmentMapper::authorAssignmentToAuthorAssignmentDto)
//                .collect(Collectors.toSet());
//    }
//
//    public static AuthorAssignmentDto authorAssignmentToAuthorAssignmentDto(AuthorAssignment authorAssignment) {
//        AuthorAssignmentDto authorAssignmentDto = new AuthorAssignmentDto();
//        authorAssignmentDto.setAuthorAssignmentId(authorAssignment.getId());
//        authorAssignmentDto.setAuthor(AuthorMapper.authorToAuthorDto(authorAssignment.getAuthor()));
//        authorAssignmentDto.setBook(BookMapper.bookToBookDto(authorAssignment.getBook()));
//        return authorAssignmentDto;
//    }
//}
