package com.dydals.board.Service;

import com.dydals.board.Dto.PostDto;
import com.dydals.board.Entity.Post;
import com.dydals.board.Repository.MemberRepositoryImpl;
import com.dydals.board.Repository.PostRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepositoryImpl postRepository;
    private final MemberRepositoryImpl memberRepository;

    public List<PostDto> getAllPost() {
        List<Post> postlist = postRepository.findAll();

        return postlist.stream()
                .map(post -> PostDto.toRequstPost(post))
                .collect(Collectors.toList());
    }

    public List<PostDto> findAll(){
        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC,"boardCategory","boardId"));

        List<PostDto> postDtoList = new ArrayList<>();
        for(Post post : postList){
            postDtoList.add(PostDto.toRequstPost(post));
        }
        return postDtoList;
    }

    public PostDto findByBoardId(Long boardId){
        Optional<Post> optionalpost = postRepository.findById(boardId);
        if(optionalpost.isPresent()){
            //DB에 값이 존재하면 Entity를 DTO로 변환
            return PostDto.toRequstPost(optionalpost.get());
        }else{
            return null;
        }
    }

    public void write(PostDto postDto, String mid){
        //게시글 작성시 최초 값 초기화
        postDto.setPost_member(memberRepository.findByMemberId(mid).orElse(null));
        postDto.setBoardCommentCnt(0l);
        postDto.setUnrecommendation(0l);
        postDto.setRecommendation(0l);
        postDto.setViews(0l);

        Post post = Post.toPost(postDto);
        postRepository.save(post);
    }

    public void deleteByBoardDTO(PostDto postDto){
        postRepository.deleteById(postDto.getId());
    }

    public PostDto update(Long id, PostDto postDto) {
        Optional<Post> findPost = postRepository.findById(id);
        if (findPost.isPresent()) {
            PostDto findPostDto = PostDto.toRequstPost(findPost.get());
            findPostDto.setTitle(postDto.getTitle());
            findPostDto.setDetail(postDto.getDetail());
            Post updatePost = Post.toPost(findPostDto);

            return PostDto.toRequstPost(postRepository.save(updatePost));
        }
        return null;
    }

    public void like(PostDto postDto){
        postDto.setRecommendation(postDto.getRecommendation()+1);
        Post post = Post.toPost(postDto);
        postRepository.save(post);
    }

    public void disLike(PostDto postDto){
        postDto.setUnrecommendation(postDto.getUnrecommendation()+1);
        Post post = Post.toPost(postDto);
        postRepository.save(post);
    }

    public void updateViewCnt(Long id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            PostDto postDto = PostDto.toRequstPost(post.get());
            postDto.setViews(postDto.getViews() + 1);
            postRepository.save(Post.toPost(postDto));
        }
    }
}
