package 혼자서_하는_틱택토;

class Solution {

    private char[][] board;
    private int rowLen, colLen;

    private int countChar(char c){
        int count = 0;
        for(int i = 0; i < rowLen; i++){
            for(int j = 0; j < colLen; j++){
                if(board[i][j] == c) count++;
            }
        }
        return count;
    }

    private int countLine(char c){
        int count = 0;

        // 가로, 세로
        for(int i = 0; i < rowLen; i++){
            if(c == board[i][0] && c == board[i][1] && c == board[i][2]) count++;
            if(c == board[0][i] && c == board[1][i] && c == board[2][i]) count++;
        }

        // 대각선
        if(c == board[0][0] && c == board[1][1] && c == board[2][2]) count++;
        if(c == board[2][0] && c == board[1][1] && c == board[0][2]) count++;

        return count;
    }

    public int solution(String[] _board) {
        board = new char[_board.length][_board[0].length()];

        rowLen = board.length;
        colLen = board[0].length;

        for(int i = 0; i < rowLen; i++){
            board[i] = _board[i].toCharArray();
        }

        // (1) O갯수, X갯수
        int OCnt = countChar('O');
        int XCnt = countChar('X');

        // (2) 가로, 세로, 대각선 갯수
        int OLineCnt = countLine('O');
        int XLineCnt = countLine('X');

        // (3) 검토
        if(!(OCnt == XCnt || OCnt == XCnt + 1)) return 0;

        if(XLineCnt > 0 && OLineCnt == XLineCnt) return 0;
        if(OLineCnt > 0 && OCnt <= XCnt) return 0;
        if(XLineCnt > 0 && OCnt > XCnt) return 0;

        return 1;
    }
}
