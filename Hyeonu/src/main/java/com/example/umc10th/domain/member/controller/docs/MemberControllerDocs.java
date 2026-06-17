package com.example.umc10th.domain.member.controller.docs;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.entity.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name= "마이페이지 API")
public interface MemberControllerDocs {

    // 마이페이지
    @Operation(
            summary= "마이페이지 API By 리우",
            description= """
                    # 마이 페이지
                    
                    ## 요청 형식
                    - 헤더: Authorize: Bearer {JWT 토큰}
                    """)
    @ApiResponses(value={
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode="200",
                    description="성공 예시",
                    content=@Content(
                            mediaType="application/json",
                            schema=@Schema(implementation= ApiResponse.class),
                            examples=@ExampleObject(value= """
                                    {
                                    "isSuccess":true,
                                    "code":"MEMBER200_1",
                                    "message":"성공적으로 유저를 조회했습니다.",
                                    "result":{
                                    "name": "리우",
                                    "email": "example@exam.com"
                                    }               
                                    }
                                    """)
                    )
            )
    })
    ResponseEntity<ApiResponse<MemberResDTO.GetInfo>> getInfo(AuthMember member);
}
